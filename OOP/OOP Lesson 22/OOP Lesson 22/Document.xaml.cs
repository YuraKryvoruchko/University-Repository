using Microsoft.Win32;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace OOP_Lesson_22
{
    /// <summary>
    /// Interaction logic for Document.xaml
    /// </summary>
    public partial class Document : UserControl
    {
        public Document(int number)
        {
            InitializeComponent();
        }

        public void SetText(string text)
        {
            TextBoxContent.Document.Blocks.Clear();
            TextBoxContent.Document.Blocks.Add(new Paragraph(new Run(text)));
        }
        public void SaveDocument()
        {
            SaveFileDialog saveFileDialog1 = new SaveFileDialog
            {
                Filter = "Text files (*.txt)|*.txt|Rich Text Format (*.rtf)|*.rtf"
            };

            if (saveFileDialog1.ShowDialog() == true)
            {
                string filePath = saveFileDialog1.FileName;
                TextRange textRange = new TextRange(TextBoxContent.Document.ContentStart, TextBoxContent.Document.ContentEnd);

                using (System.IO.FileStream fileStream = new System.IO.FileStream(filePath, System.IO.FileMode.Create))
                {
                    if (filePath.EndsWith(".rtf"))
                    {
                        textRange.Save(fileStream, DataFormats.Rtf);
                    }
                    else
                    {
                        textRange.Save(fileStream, DataFormats.Text);
                    }
                }
            }
        }

        public void Cut()
        {
            TextBoxContent.Cut();
        }

        public void Copy()
        {
            TextBoxContent.Copy();
        }

        public void Paste()
        {
            TextBoxContent.Paste();
        }

        public void SelectAll()
        {
            TextBoxContent.SelectAll();
        }

        public void InsertImage()
        {
            OpenFileDialog openFileDialog = new OpenFileDialog
            {
                Filter = "Image files (*.png;*.jpeg;*.jpg;*.gif)|*.png;*.jpeg;*.jpg;*.gif|All files (*.*)|*.*",
                RestoreDirectory = true
            };

            if (openFileDialog.ShowDialog() == true)
            {
                string filePath = openFileDialog.FileName;
                BitmapImage bitmap = new BitmapImage(new Uri(filePath));

                Image image = new Image
                {
                    Source = bitmap,
                    Width = bitmap.PixelWidth,
                    Height = bitmap.PixelHeight
                };

                TextPointer tp = TextBoxContent.CaretPosition.GetInsertionPosition(LogicalDirection.Forward);
                InlineUIContainer container = new InlineUIContainer(image, tp);

            }
        }

        public void ApplyBold()
        {
            ApplyOrRemoveBold(FontWeights.Bold);
        }

        public void RemoveBold()
        {
            ApplyOrRemoveBold(FontWeights.Normal);
        }

        private void ApplyOrRemoveBold(FontWeight fontWeight)
        {
            TextRange textRange = new TextRange(TextBoxContent.Selection.Start, TextBoxContent.Selection.End);

            if (textRange.IsEmpty)
            {
                TextBoxContent.FontWeight = fontWeight;
            }
            else
            {
                object currentValue = textRange.GetPropertyValue(TextElement.FontWeightProperty);
                if (currentValue != null && currentValue.Equals(fontWeight))
                {
                    textRange.ApplyPropertyValue(TextElement.FontWeightProperty, FontWeights.Normal);
                }
                else
                {
                    textRange.ApplyPropertyValue(TextElement.FontWeightProperty, fontWeight);
                }
            }
        }

        public void ApplyItalic()
        {
            ApplyOrRemoveItalic(FontStyles.Italic);
        }

        public void RemoveItalic()
        {
            ApplyOrRemoveItalic(FontStyles.Normal);
        }

        private void ApplyOrRemoveItalic(FontStyle fontStyle)
        {
            TextRange textRange = new TextRange(TextBoxContent.Selection.Start, TextBoxContent.Selection.End);

            if (textRange.IsEmpty)
            {
                TextBoxContent.FontStyle = fontStyle;
            }
            else
            {
                object currentValue = textRange.GetPropertyValue(TextElement.FontStyleProperty);
                if (currentValue != null && currentValue.Equals(fontStyle))
                {
                    textRange.ApplyPropertyValue(TextElement.FontStyleProperty, FontStyles.Normal);
                }
                else
                {
                    textRange.ApplyPropertyValue(TextElement.FontStyleProperty, fontStyle);
                }
            }
        }

        public void ApplyUnderline()
        {
            ApplyOrRemoveStyle(Inline.TextDecorationsProperty, TextDecorations.Underline);
        }
        public void RemoveUnderline()
        {
            ApplyOrRemoveStyle(Inline.TextDecorationsProperty, null);
        }

        private void ApplyOrRemoveStyle(DependencyProperty property, object value)
        {
            TextRange textRange = new TextRange(TextBoxContent.Selection.Start, TextBoxContent.Selection.End);
            object currentValue = textRange.GetPropertyValue(property);

            if (currentValue != null && currentValue.Equals(value))
            {
                textRange.ApplyPropertyValue(property, null);
            }
            else
            {
                textRange.ApplyPropertyValue(property, value);
            }
        }

        public void ApplyFontFamily(FontFamily fontFamily)
        {
            if (TextBoxContent.Selection.IsEmpty)
                return;

            TextRange textRange = new TextRange(TextBoxContent.Selection.Start, TextBoxContent.Selection.End);
            textRange.ApplyPropertyValue(TextElement.FontFamilyProperty, fontFamily);
        }

        public void ApplyFontSize(double fontSize)
        {
            if (TextBoxContent.Selection.IsEmpty)
                return;

            TextRange textRange = new TextRange(TextBoxContent.Selection.Start, TextBoxContent.Selection.End);
            textRange.ApplyPropertyValue(TextElement.FontSizeProperty, fontSize);
        }

        public void SetAlignment(HorizontalAlignment alignment)
        {
            if (TextBoxContent != null)
            {
                Paragraph paragraph = TextBoxContent.CaretPosition.Paragraph;
                if (paragraph != null)
                {
                    paragraph.TextAlignment = ConvertToTextAlignment(alignment);
                }

            }
        }
        private TextAlignment ConvertToTextAlignment(HorizontalAlignment alignment)
        {
            switch (alignment)
            {
                case HorizontalAlignment.Left:
                    return TextAlignment.Left;
                case HorizontalAlignment.Center:
                    return TextAlignment.Center;
                case HorizontalAlignment.Right:
                    return TextAlignment.Right;
                default:
                    throw new ArgumentException("Непідтримуване значення HorizontalAlignment");
            }
        }
    }
}
