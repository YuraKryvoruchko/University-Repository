using System;
using System.IO;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Xml.Linq;

namespace OOP_Lesson_21
{
    public partial class Form1 : Form
    {
        private Document document;
        public Form1()
        {
            InitializeComponent();
        }

        private int number = 1;

        private void createToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Document f = new Document(number);
            number++;
            f.MdiParent = this;
            f.Show();
        }

        private void openToolStripMenuItem_Click(object sender, EventArgs e)
        {
            OpenFileDialog openFileDialog1 = new OpenFileDialog();

            openFileDialog1.Filter = "Text files (*.txt)|*.txt|All files (*.*)|*.*";
            openFileDialog1.RestoreDirectory = true;

            if (openFileDialog1.ShowDialog() == DialogResult.OK)
            {
                string filePath = openFileDialog1.FileName;
                string fileText = File.ReadAllText(filePath);
                Document activeDocument = this.ActiveMdiChild as Document;

                if (activeDocument != null)
                {
                    activeDocument.SetText(fileText);
                }
                else
                {
                    MessageBox.Show("No active document to open the file into.");
                }
            }
        }

        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Document activeDoc = (Document)this.ActiveMdiChild;
            if (activeDoc != null)
            {
                activeDoc.SaveDocument();
            }
        }

        private void saveAsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Document activeDoc = (Document)this.ActiveMdiChild;
            if (activeDoc != null)
            {
                activeDoc.SaveAsDocument();
            }
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void cutToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Document activeDoc = (Document)this.ActiveMdiChild;
            if (activeDoc != null)
            {
                activeDoc.Cut();
            }
        }

        private void copyToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Document activeDoc = (Document)this.ActiveMdiChild;
            if (activeDoc != null)
            {
                activeDoc.Copy();
            }
        }

        private void pasteToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Document activeDoc = (Document)this.ActiveMdiChild;
            if (activeDoc != null)
            {
                activeDoc.Paste();
            }
        }

        private void selectAllToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Document activeDoc = (Document)this.ActiveMdiChild;
            if (activeDoc != null)
            {
                activeDoc.SelectAll();
            }
        }

        private void deleteToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Document activeDoc = (Document)this.ActiveMdiChild;
            if (activeDoc != null)
            {
                activeDoc.Delete();
            }
        }

        private void selectImageToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Document activeDoc = (Document)this.ActiveMdiChild;
            if (activeDoc != null)
            {
                activeDoc.InsertImageMenuItem_Click();
            }

        }

        private void arrangeIconsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.LayoutMdi(MdiLayout.ArrangeIcons);
        }

        private void cascadeToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.LayoutMdi(MdiLayout.Cascade);
        }

        private void horizontalToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.LayoutMdi(MdiLayout.TileHorizontal);
        }

        private void verticalToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.LayoutMdi(MdiLayout.TileVertical);
        }

        private void ChangeLanguage(string culture)
        {
            Thread.CurrentThread.CurrentUICulture = new CultureInfo(culture);
            ApplyLanguage();
        }

        private void ukraineToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ChangeLanguage("uk");
        }

        private void englishToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ChangeLanguage("en");
        }

        private void ApplyLanguage()
        {
            fileToolStripMenuItem.Text = Properties.Resources.menuFile;
            editToolStripMenuItem.Text = Properties.Resources.menuEdit;
            paste1ToolStripMenuItem.Text = Properties.Resources.menuIn;
            toolStripMenuItem1.Text = Properties.Resources.menuFormat;
            windowToolStripMenuItem.Text = Properties.Resources.menuWindow;
            languagesToolStripMenuItem.Text = Properties.Resources.menuLeng;

            createToolStripMenuItem.Text = Properties.Resources.Create;
            openToolStripMenuItem.Text = Properties.Resources.Open;
            saveToolStripMenuItem.Text = Properties.Resources.String2;
            saveAsToolStripMenuItem.Text = Properties.Resources.String3;
            exitToolStripMenuItem.Text = Properties.Resources.String4;

            cutToolStripMenuItem.Text = Properties.Resources.String5;
            pasteToolStripMenuItem.Text = Properties.Resources.String6;
            copyToolStripMenuItem.Text = Properties.Resources.String7;
            selectAllВсеToolStripMenuItem.Text = Properties.Resources.String8;
            deleteToolStripMenuItem.Text = Properties.Resources.String9;

            selectImageToolStripMenuItem.Text = Properties.Resources.String10;

            toolStripMenuItem2.Text = Properties.Resources.String11;
            fontColorToolStripMenuItem.Text = Properties.Resources.String12;
            alignmentToolStripMenuItem.Text = Properties.Resources.String13;

            windowToolStripMenuItem.Text = Properties.Resources.String14;
            cascadeToolStripMenuItem.Text = Properties.Resources.String15;
            horizontalToolStripMenuItem.Text = Properties.Resources.String16;
            verticalToolStripMenuItem.Text = Properties.Resources.String17;

            leftToolStripMenuItem.Text = Properties.Resources.String1;
            centerToolStripMenuItem.Text = Properties.Resources.String18;
            rightToolStripMenuItem.Text = Properties.Resources.String19;

            toolStripButton1.Text = Properties.Resources.String20;
            toolStripButton2.Text = Properties.Resources.String21;
            toolStripButton3.Text = Properties.Resources.String22;
            toolStripButton4.Text = Properties.Resources.String23;
            toolStripButton5.Text = Properties.Resources.String24;
            toolStripButton6.Text = Properties.Resources.String25;
            toolStripButton7.Text = Properties.Resources.String26;
            toolStripButton8.Text = Properties.Resources.String27;

            englishToolStripMenuItem.Text = "English";
            ukraineToolStripMenuItem.Text = "Українська";
        }

        private void leftToolStripMenuItem_Click(object sender, EventArgs e)
        {
            SetAlignmentForActiveDocument(HorizontalAlignment.Left);
        }

        private void SetAlignmentForActiveDocument(HorizontalAlignment alignment)
        {
            Document activeDoc = (Document)this.ActiveMdiChild;
            if (activeDoc != null)
            {
                activeDoc.SetAlignment(alignment);
            }
        }

        private void centerToolStripMenuItem_Click(object sender, EventArgs e)
        {
            SetAlignmentForActiveDocument(HorizontalAlignment.Center);
        }

        private void rightToolStripMenuItem_Click(object sender, EventArgs e)
        {
            SetAlignmentForActiveDocument(HorizontalAlignment.Right);
        }

        private void toolStripMenuItem2_Click(object sender, EventArgs e)
        {
            FontDialog fontDialog = new FontDialog();
            DialogResult result = fontDialog.ShowDialog();

            if (result == DialogResult.OK)
            {
                Font selectedFont = fontDialog.Font;

                Document activeDoc = this.ActiveMdiChild as Document;
                if (activeDoc != null)
                {
                    activeDoc.SetText(selectedFont);
                }
            }
        }

        private void fontColorToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ColorDialog colorDialog = new ColorDialog();
            DialogResult result = colorDialog.ShowDialog();

            if (result == DialogResult.OK)
            {
                Color selectedColor = colorDialog.Color;

                Document activeDoc = this.ActiveMdiChild as Document;
                if (activeDoc != null)
                {
                    activeDoc.SetTextColor(selectedColor);
                }
            }
        }

        private void toolStripButton1_Click(object sender, EventArgs e)
        {
            Document f = new Document(number);
            number++;
            f.MdiParent = this;
            f.Show();
        }

        private void toolStripButton2_Click(object sender, EventArgs e)
        {
            Document activeDoc = (Document)this.ActiveMdiChild;
            if (activeDoc != null)
            {
                activeDoc.SaveDocument();
            }
        }

        private void toolStripButton3_Click(object sender, EventArgs e)
        {
            Document activeDoc = (Document)this.ActiveMdiChild;
            if (activeDoc != null)
            {
                activeDoc.Copy();
            }
        }

        private void toolStripButton4_Click(object sender, EventArgs e)
        {
            Document activeDoc = (Document)this.ActiveMdiChild;
            if (activeDoc != null)
            {
                activeDoc.Paste();
            }
        }

        private void toolStripButton5_Click(object sender, EventArgs e)
        {
            Document activeDoc = (Document)this.ActiveMdiChild;
            if (activeDoc != null)
            {
                activeDoc.InsertImageMenuItem_Click();
            }
        }

        private bool isFontBold = false;

        private void toolStripButton6_Click(object sender, EventArgs e)
        {
            Document activeDoc = this.ActiveMdiChild as Document;

            if (activeDoc != null)
            {
                if (!isFontBold)
                {
                    activeDoc.SelectionFont(new Font(activeDoc.Font, FontStyle.Bold));
                    isFontBold = true;
                }
                else
                {
                    activeDoc.SelectionFont(new Font(activeDoc.Font, FontStyle.Regular));
                    isFontBold = false;
                }
            }

        }

        private bool isFontItalic = false;

        private void toolStripButton7_Click(object sender, EventArgs e)
        {
            Document activeDoc = this.ActiveMdiChild as Document;

            if (activeDoc != null)
            {
                if (!isFontItalic)
                {
                    activeDoc.SelectionFont(new Font(activeDoc.Font, FontStyle.Italic));
                    isFontItalic = true;
                }
                else
                {
                    activeDoc.SelectionFont(new Font(activeDoc.Font, FontStyle.Regular));
                    isFontItalic = false;
                }
            }
        }

        private bool isFontUnderline = false;
        private void toolStripButton8_Click(object sender, EventArgs e)
        {
            Document activeDoc = this.ActiveMdiChild as Document;

            if (activeDoc != null)
            {
                if (!isFontUnderline)
                {
                    activeDoc.SelectionFont(new Font(activeDoc.Font, FontStyle.Underline));
                    isFontUnderline = true;
                }
                else
                {
                    activeDoc.SelectionFont(new Font(activeDoc.Font, FontStyle.Regular));
                    isFontUnderline = false;
                }
            }
        }
    }
}
