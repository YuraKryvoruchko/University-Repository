using Microsoft.Win32;
using System.Reflection.Metadata;
using System.Text;
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
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            cmbFontFamily.ItemsSource = Fonts.SystemFontFamilies.OrderBy(f => f.Source);
            cmbFontFamily.SelectionChanged += CmbFontFamily_SelectionChanged;
            cmbFontSize.ItemsSource = new List<double>() { 8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72 };
            cmbFontSize.SelectionChanged += CmbFontSize_SelectionChanged;
        }

        private void CmbFontFamily_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (TabControl.SelectedItem is TabItem selectedTabItem && selectedTabItem.Content is Document document)
            {
                ComboBox comboBox = sender as ComboBox;
                if (comboBox != null && comboBox.SelectedItem != null)
                {
                    string fontFamily = comboBox.SelectedItem.ToString();
                    document.ApplyFontFamily(new FontFamily(fontFamily));
                }
            }
        }

        private void CmbFontSize_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (TabControl.SelectedItem is TabItem selectedTabItem && selectedTabItem.Content is Document document)
            {
                ComboBox comboBox = sender as ComboBox;
                if (comboBox != null && comboBox.SelectedItem != null)
                {
                    double fontSize = (double)comboBox.SelectedItem;
                    document.ApplyFontSize(fontSize);
                }
            }
        }

        private int number = 1;
        private void NewDocument_Click(object sender, RoutedEventArgs e)
        {
            TabItem newTab = new TabItem();
            newTab.Header = $"Документ {number}";
            Document document = new Document(number);
            newTab.Content = document;
            TabControl.Items.Add(newTab);
            TabControl.SelectedItem = newTab;
            number++;
        }

        private void Open_Click(object sender, RoutedEventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog
            {
                Filter = "Text files (*.txt)|*.txt|All files (*.*)|*.*",
                RestoreDirectory = true
            };

            if (openFileDialog.ShowDialog() == true)
            {
                string filePath = openFileDialog.FileName;
                string fileText = System.IO.File.ReadAllText(filePath);

                if (TabControl.SelectedItem is TabItem selectedTabItem && selectedTabItem.Content is Document activeDocument)
                {
                    activeDocument.SetText(fileText);
                }
                else
                {
                    MessageBox.Show("Немає активного документу всередині");
                }
            }
        }

        private void Save_Click(object sender, RoutedEventArgs e)
        {
            if (TabControl.SelectedItem is TabItem selectedTabItem && selectedTabItem.Content is Document activeDocument)
            {
                activeDocument.SaveDocument();
            }
        }

        private void Close_Click(object sender, RoutedEventArgs e)
        {
            Close();
        }

        private void CutMenuItem_Click(object sender, RoutedEventArgs e)
        {
            if (TabControl.SelectedItem is TabItem selectedTabItem && selectedTabItem.Content is Document activeDocument)
            {
                activeDocument.Cut();
            }
        }

        private void CopyMenuItem_Click(object sender, RoutedEventArgs e)
        {
            if (TabControl.SelectedItem is TabItem selectedTabItem && selectedTabItem.Content is Document activeDocument)
            {
                activeDocument.Copy();
            }
        }

        private void PasteMenuItem_Click(object sender, RoutedEventArgs e)
        {
            if (TabControl.SelectedItem is TabItem selectedTabItem && selectedTabItem.Content is Document activeDocument)
            {
                activeDocument.Paste();
            }
        }

        private void SelectAllMenuItem_Click(object sender, RoutedEventArgs e)
        {
            if (TabControl.SelectedItem is TabItem selectedTabItem && selectedTabItem.Content is Document activeDocument)
            {
                activeDocument.SelectAll();
            }
        }

        private void EnglishMenuItem_Click(object sender, RoutedEventArgs e)
        {
            fileMenu.Header = "File";
            createMenuItem.Header = "Create";
            openMenuItem.Header = "Open";
            saveMenuItem.Header = "Save";
            exitMenuItem.Header = "Exit";

            editMenu.Header = "Edit";
            copyMenuItem.Header = "Copy";
            selectAllMenuItem.Header = "Select All";
            pasteMenuItem.Header = "Paste";
            cutMenuItem.Header = "Cut";
            pasteimageMenuItem.Header = "Insert image";

            languageMenu.Header = "Language";
        }

        private void PasteImage_Click(object sender, RoutedEventArgs e)
        {
            if (TabControl.SelectedItem is TabItem selectedTabItem && selectedTabItem.Content is Document activeDocument)
            {
                activeDocument.InsertImage();
            }
        }

        private void UkrainianMenuItem_Click(object sender, RoutedEventArgs e)
        {

            fileMenu.Header = "Файл";
            createMenuItem.Header = "Створити";
            openMenuItem.Header = "Відкрити";
            saveMenuItem.Header = "Зберегти";
            exitMenuItem.Header = "Вийти";

            editMenu.Header = "Редагувати";
            copyMenuItem.Header = "Копіювати";
            selectAllMenuItem.Header = "Виділити все";
            pasteMenuItem.Header = "Вставити";
            cutMenuItem.Header = "Вирізати";
            pasteimageMenuItem.Header = "Вставити зображення";

            languageMenu.Header = "Мова";
        }

        private void BoldButton_Click(object sender, RoutedEventArgs e)
        {
            if (TabControl.SelectedItem is TabItem selectedTabItem && selectedTabItem.Content is Document document)
            {
                document.ApplyBold();
            }
        }

        private void ItalicButton_Click(object sender, RoutedEventArgs e)
        {
            if (TabControl.SelectedItem is TabItem selectedTabItem && selectedTabItem.Content is Document document)
            {
                document.ApplyItalic();
            }
        }

        private void UnderlineButton_Click(object sender, RoutedEventArgs e)
        {
            if (TabControl.SelectedItem is TabItem selectedTabItem && selectedTabItem.Content is Document document)
            {
                document.ApplyUnderline();
            }
        }

        private void RemoveBold_Click(object sender, RoutedEventArgs e)
        {
            if (TabControl.SelectedItem is TabItem selectedTabItem && selectedTabItem.Content is Document document)
            {
                document.RemoveBold();
            }
        }

        private void RemoveItalic_Click(object sender, RoutedEventArgs e)
        {
            if (TabControl.SelectedItem is TabItem selectedTabItem && selectedTabItem.Content is Document document)
            {
                document.RemoveItalic();
            }
        }

        private void RemoveUnderline_Click(object sender, RoutedEventArgs e)
        {
            if (TabControl.SelectedItem is TabItem selectedTabItem && selectedTabItem.Content is Document document)
            {
                document.RemoveUnderline();
            }
        }

        private void PasteImageButton_Click(object sender, RoutedEventArgs e)
        {
            if (TabControl.SelectedItem is TabItem selectedTabItem && selectedTabItem.Content is Document activeDocument)
            {
                activeDocument.InsertImage();
            }
        }

        private void LeftIcon_Click(object sender, RoutedEventArgs e)
        {
            SetAlignmentForActiveDocument(HorizontalAlignment.Left);
        }

        private void CenterIcon_Click(object sender, RoutedEventArgs e)
        {
            SetAlignmentForActiveDocument(HorizontalAlignment.Center);
        }

        private void RightIcon_Click(object sender, RoutedEventArgs e)
        {
            SetAlignmentForActiveDocument(HorizontalAlignment.Right);
        }

        private void SetAlignmentForActiveDocument(HorizontalAlignment alignment)
        {
            if (TabControl.SelectedItem is TabItem selectedTabItem && selectedTabItem.Content is Document activeDocument)
            {
                activeDocument.SetAlignment(alignment);
            }
        }
    }
}