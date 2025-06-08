using System;
using System.Drawing;
using System.Windows.Forms;

namespace OOP_Lesson_29
{
    public partial class SettingsForm : Form
    {
        private Form1 mainForm;
        public SettingsForm(Form1 form)
        {
            mainForm = form;
            InitializeComponent();

            foreach (FontFamily font in FontFamily.Families)
            {
                fontComboBox.Items.Add(font.Name);
            }
            fontComboBox.SelectedIndex = 0;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                string host = hostTextBox.Text;
                int remotePort = int.Parse(remotePortTextBox.Text);
                int ttl = int.Parse(ttlTextBox.Text);
                Font font = new Font(fontComboBox.SelectedItem.ToString(), int.Parse(fontSizeTextBox.Text));

                mainForm.UpdateSettings(host, remotePort, ttl, font);
                this.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

    }
}
