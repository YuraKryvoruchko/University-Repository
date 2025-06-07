using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace OOP_Lesson_26
{
    public partial class Form1 : Form
    {
        private Dictionary<string, string> templates = new Dictionary<string, string>()
        {
            { "Шаблон 1", "C:\\Users\\User\\source\\repos\\University-Repository\\OOP\\OOP Lesson 26\\OOP Lesson 26\\Resources\\Шаблон1.docx" },
            { "Шаблон 2", "C:\\Users\\User\\source\\repos\\University-Repository\\OOP\\OOP Lesson 26\\OOP Lesson 26\\Resources\\Шаблон2.docx" }
        };

        private string newDocumentPath;

        public Form1()
        {
            InitializeComponent();

            comboBoxTemplates.DataSource = new BindingSource(templates, null);
            comboBoxTemplates.DisplayMember = "Key";
            comboBoxTemplates.ValueMember = "Value";
        }

        private void button1_Click_1(object sender, EventArgs e)
        {
            string selectedTemplate = ((KeyValuePair<string, string>)comboBoxTemplates.SelectedItem).Value;
            var helper = new WordHelper(selectedTemplate);

            var items = new Dictionary<string, string>
            {
                {"[Дата]", textBox1.Text },
                {"[ПІБ відправника]", textBox2.Text },
                {"[Посада відправника]", textBox3.Text },
                {"[Компанія відправника]", textBox4.Text },
                {"[Вулиця, будинок відправника]", textBox5.Text },
                {"[Місто відправника]", textBox7.Text },
                {"[Область відправника]", textBox8.Text },
                {"[Поштовий індекс відправника]", textBox9.Text },

                {"[ПІБ одержувача]", textBox10.Text },
                {"[Посада одержувача]", textBox12.Text },
                {"[Компанія одержувача]", textBox11.Text },
                {"[Вулиця, будинок одержувача]", textBox14.Text },
                {"[Місто одержувача]", textBox16.Text },
                {"[Область одержувача]", textBox15.Text },
                {"[Поштовий індекс одержувача]", textBox17.Text },
            };

            newDocumentPath = helper.Process(items);
            if (!string.IsNullOrEmpty(newDocumentPath))
            {
                MessageBox.Show($"Документ створено та збережено!\nПосилання: {newDocumentPath}");
            }
            else
            {
                MessageBox.Show("Не вдалося створити документ!");
            }
        }

        private void button3_Click_1(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(newDocumentPath))
            {
                MessageBox.Show("Не вдалося знайти документ.");
                return;
            }

            var helper = new WordHelper(newDocumentPath);

            bool result = helper.FindAndReplace(textBoxFind.Text, textBoxReplace.Text);
            if (result)
            {
                MessageBox.Show("Документ замінено успішно.");
            }
            else
            {
                MessageBox.Show("Не вдалося замінити документ.");
            }
        }
    }
}
