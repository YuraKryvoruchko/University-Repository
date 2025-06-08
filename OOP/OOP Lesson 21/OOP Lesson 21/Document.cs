using System.Drawing;
using System.Windows.Forms;

namespace OOP_Lesson_21
{
    public partial class Document : Form
    {
        public Document(int number)
        {
            InitializeComponent();
            Text = "Документ " + number.ToString();

        }

        public void SetText(string text)
        {
            richTextBox1.Text = text;
        }

        public void SaveDocument()
        {
            SaveFileDialog saveFileDialog1 = new SaveFileDialog();
            saveFileDialog1.Filter = "Text files (*.txt)|*.txt|Rich Text Format (*.rtf)|*.rtf";

            if (saveFileDialog1.ShowDialog() == DialogResult.OK)
            {
                string filePath = saveFileDialog1.FileName;

                if (filePath.EndsWith(".rtf"))
                {
                    richTextBox1.SaveFile(filePath, RichTextBoxStreamType.RichText);
                }
                else
                {
                    richTextBox1.SaveFile(filePath, RichTextBoxStreamType.PlainText);
                }
            }
        }

        public void SaveAsDocument()
        {
            SaveFileDialog saveFileDialog1 = new SaveFileDialog();
            saveFileDialog1.Filter = "Text files (*.txt)|*.txt|Rich Text Format (*.rtf)|*.rtf";

            if (saveFileDialog1.ShowDialog() == DialogResult.OK)
            {
                string filePath = saveFileDialog1.FileName;

                if (filePath.EndsWith(".rtf"))
                {
                    richTextBox1.SaveFile(filePath, RichTextBoxStreamType.RichText);
                }
                else
                {
                    richTextBox1.SaveFile(filePath, RichTextBoxStreamType.PlainText);
                }
            }
        }

        public void Cut()
        {
            richTextBox1.Cut();
        }

        public void Copy()
        {
            richTextBox1.Copy();
        }

        public void Paste()
        {
            richTextBox1.Paste();
        }

        public void SelectAll()
        {
            richTextBox1.SelectAll();
        }

        public void Delete()
        {
            richTextBox1.SelectedText = "";
        }

        public void InsertImageMenuItem_Click()
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            openFileDialog.Filter = "Image Files (*.bmp;*.jpg;*.jpeg;*.gif;*.png)|*.BMP;*.JPG;*.JPEG;*.GIF;*.PNG";

            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                string imagePath = openFileDialog.FileName;

                Image image = Image.FromFile(imagePath);

                Clipboard.SetImage(image);
                richTextBox1.Paste();
            }
        }

        public void SetAlignment(HorizontalAlignment alignment)
        {
            richTextBox1.SelectionAlignment = alignment;
        }

        public void SetText(Font newFont)
        {
            richTextBox1.SelectionFont = newFont;
        }

        public void SetTextColor(Color selectedColor)
        {
            richTextBox1.SelectionColor = selectedColor;
        }

        public void SelectionFont(Font selectedFont)
        {
            richTextBox1.SelectionFont = selectedFont;
        }
    }
}
