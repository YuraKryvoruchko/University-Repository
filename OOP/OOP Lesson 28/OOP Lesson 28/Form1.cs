using System;
using System.Data;
using System.IO;
using System.Linq;
using System.Security.AccessControl;
using System.Windows.Forms;

namespace OOP_Lesson_28
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();

            DriveInfo[] drives = DriveInfo.GetDrives();

            foreach (DriveInfo drive in drives)
            {
                listBox1.Items.Add(drive.Name);
                listBox1.Items.Add("");
            }
        }

        private void button1_Click_1(object sender, EventArgs e)
        {
            DriveInfo selectedDrive = null;

            if (listBox1.SelectedItem != null)
            {
                string selectedItem = listBox1.SelectedItem.ToString();
                string selectedDriveName = selectedItem.Split(':')[0];

                foreach (DriveInfo drive in DriveInfo.GetDrives())
                {
                    if (drive.Name.StartsWith(selectedDriveName, StringComparison.OrdinalIgnoreCase))
                    {
                        selectedDrive = drive;
                        break;
                    }
                }

                if (selectedDrive != null)
                {
                    MessageBox.Show($"Назва диску: {selectedDrive.Name}\n" +
                                    $"Тип диску: {selectedDrive.DriveType}\n" +
                                    $"Ємкість диску: {(selectedDrive.TotalSize / (1024 * 1024 * 1024)).ToString()} ГБ\n" +
                                    $"Вільна ємкість диску: {(selectedDrive.TotalFreeSpace / (1024 * 1024 * 1024)).ToString()} ГБ");
                }
                else
                {
                    MessageBox.Show("Оберіть диск.");
                }
            }
            else
            {
                MessageBox.Show("Оберіть диск.");
            }
        }

        private void button2_Click_1(object sender, EventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            openFileDialog.Title = "Вікно переміщення файлу!";

            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                string sourcePath = openFileDialog.FileName;

                SaveFileDialog saveFileDialog = new SaveFileDialog();
                saveFileDialog.Title = "Збереження";

                if (saveFileDialog.ShowDialog() == DialogResult.OK)
                {
                    string targetPath = saveFileDialog.FileName;

                    try
                    {
                        File.Move(sourcePath, targetPath);
                        MessageBox.Show("Файо переміщено.");
                    }
                    catch (IOException ex)
                    {
                        MessageBox.Show("Файл не вдалося пересістити³: " + ex.Message);
                    }
                }
            }
        }

        private void button3_Click_1(object sender, EventArgs e)
        {
            DirectoryInfo selectedDirectory = null;
            FileInfo selectedFile = null;

            if (listBox2.SelectedItem != null)
            {
                string selectedItem = listBox2.SelectedItem.ToString();
                selectedDirectory = new DirectoryInfo(selectedItem);

                if (selectedDirectory.Exists)
                {
                    MessageBox.Show($"Назва директорії: {selectedDirectory.FullName}\n" +
                                    $"Дата створення: {selectedDirectory.CreationTime}\n" +
                                    $"Дата останньої змінни: {selectedDirectory.LastWriteTime}\n" +
                                    $"Кількість файлів: {selectedDirectory.GetFiles().Length}\n" +
                                    $"Кількість директорій: {selectedDirectory.GetDirectories().Length}");
                }
                else
                {
                    selectedFile = new FileInfo(selectedItem);

                    if (selectedFile.Exists)
                    {
                        MessageBox.Show($"Назва файлу: {selectedFile.Name}\n" +
                                       $"Вага: {(selectedFile.Length / (1024)).ToString()} KB\n" +
                                       $"Дата створення: {selectedFile.CreationTime}\n" +
                                       $"Дата останньої змінни: {selectedFile.LastWriteTime}");
                    }
                    else
                    {
                        MessageBox.Show("Такого файлу не існує.");
                    }
                }
            }
            else
            {
                MessageBox.Show("Оберіть елемент!");
            }
        }

        private void button4_Click_1(object sender, EventArgs e)
        {
            DirectoryInfo selectedDirectory = null;

            if (listBox1.SelectedItem != null)
            {
                string selectedItem = listBox1.SelectedItem.ToString();
                selectedDirectory = new DirectoryInfo(selectedItem);

                if (selectedDirectory.Exists)
                {
                    listBox2.Items.Clear();

                    string[] dirs = Directory.GetDirectories(selectedItem);
                    listBox2.Items.Add("Каталоги:");
                    foreach (string s in dirs)
                    {
                        listBox2.Items.Add(s);
                    }
                    string[] files = Directory.GetFiles(selectedItem);
                    listBox2.Items.Add("Файли:");
                    foreach (string s in files)
                    {
                        listBox2.Items.Add(s);
                    }
                }
                else
                {
                    listBox2.Items.Clear();
                }
            }
            else
            {
                listBox2.Items.Clear();
            }
        }

        private void button5_Click_1(object sender, EventArgs e)
        {
            if (listBox1.SelectedItem != null)
            {
                string selectedDrive = listBox1.SelectedItem.ToString();
                string rootPath = Path.GetPathRoot(selectedDrive);

                string[] allDirs = Directory.GetDirectories(rootPath);
                var filteredDirs = allDirs.Where(dir => dir.Contains(textBox1.Text));

                listBox3.Items.Clear();

                foreach (string dir in filteredDirs)
                {
                    listBox3.Items.Add(dir);
                }
            }
            else
            {
                MessageBox.Show("Оберіть каталог.");
            }
        }

        private void button8_Click_1(object sender, EventArgs e)
        {
            if (listBox1.SelectedItem != null)
            {
                string selectedDrive = listBox1.SelectedItem.ToString();

                using (FolderBrowserDialog folderDialog = new FolderBrowserDialog())
                {
                    folderDialog.Description = "Створення нового каталогу";
                    folderDialog.SelectedPath = selectedDrive;

                    if (folderDialog.ShowDialog() == DialogResult.OK)
                    {
                        string newDirectoryPath = Path.Combine(folderDialog.SelectedPath, "NewDirectory");

                        try
                        {
                            Directory.CreateDirectory(newDirectoryPath);
                            MessageBox.Show("Каталог створено.");
                        }
                        catch (Exception ex)
                        {
                            MessageBox.Show($"Під час створення каталога виникла помилка: {ex.Message}");
                        }
                    }
                }
            }
            else
            {
                MessageBox.Show("Оберіть елемент.");
            }
        }

        private void button7_Click_1(object sender, EventArgs e)
        {
            listBox5.Items.Clear();

            if (listBox2.SelectedItem != null)
            {
                string selectedItem = listBox2.SelectedItem.ToString();

                try
                {
                    FileAttributes attr = File.GetAttributes(selectedItem);

                    if ((attr & FileAttributes.Directory) == FileAttributes.Directory)
                    {
                        DirectoryInfo selectedDirectory = new DirectoryInfo(selectedItem);

                        if (selectedDirectory.Exists)
                        {
                            foreach (var dirInfo in selectedDirectory.GetAccessControl().GetAccessRules(true, true, typeof(System.Security.Principal.NTAccount)))
                            {
                                if (dirInfo is FileSystemAccessRule fileRule)
                                {
                                    listBox5.Items.Add($"Користувач: {fileRule.IdentityReference}");
                                    listBox5.Items.Add($"Тип доступу: {fileRule.FileSystemRights}");
                                    listBox5.Items.Add($"Дозвіл: {fileRule.AccessControlType}");
                                    listBox5.Items.Add("-------------------------------------------");
                                }
                            }
                        }
                        else
                        {
                            MessageBox.Show("Такої категорії не існує.");
                        }
                    }
                    else
                    {
                        FileInfo selectedFile = new FileInfo(selectedItem);

                        if (selectedFile.Exists)
                        {
                            foreach (var fileInfo in selectedFile.GetAccessControl().GetAccessRules(true, true, typeof(System.Security.Principal.NTAccount)))
                            {
                                if (fileInfo is FileSystemAccessRule fileRule)
                                {
                                    listBox5.Items.Add($"Користувач: {fileRule.IdentityReference}");
                                    listBox5.Items.Add($"Тип доступу: {fileRule.FileSystemRights}");
                                    listBox5.Items.Add($"Дозвіл: {fileRule.AccessControlType}");
                                    listBox5.Items.Add("-------------------------------------------");
                                }
                            }
                        }
                        else
                        {
                            MessageBox.Show("Такого файлу не існує.");
                        }
                    }
                }
                catch (UnauthorizedAccessException ex)
                {
                    MessageBox.Show($"Помилка доступу: {ex.Message}");
                }
                catch (Exception ex)
                {
                    MessageBox.Show($"Помилка: {ex.Message}");
                }
            }
            else
            {
                MessageBox.Show("Оберіть елемент.");
            }
        }

        private void button6_Click_1(object sender, EventArgs e)
        {
            FileInfo selectedFile = null;
            listBox5.Items.Clear();
            if (listBox2.SelectedItem != null)
            {
                string path = listBox2.SelectedItem.ToString();

                try
                {
                    string readText = File.ReadAllText(path);
                    listBox4.Items.Add(readText);
                }
                catch (Exception ex)
                {
                    MessageBox.Show($"При читанні файлу виникла помилка: {ex.Message}");
                }
            }
            else
            {
                MessageBox.Show("Оберіть файл для читання.");
            }
        }

        private void button9_Click_1(object sender, EventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            openFileDialog.Title = "Оберіть файл для копіювання!";

            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                string sourcePath = openFileDialog.FileName;

                SaveFileDialog saveFileDialog = new SaveFileDialog();
                saveFileDialog.Title = "Оберіть місце збереження файлу";

                if (saveFileDialog.ShowDialog() == DialogResult.OK)
                {
                    string targetPath = saveFileDialog.FileName;

                    try
                    {
                        File.Copy(sourcePath, targetPath);
                        MessageBox.Show("Копіювання відбулося успішно.");
                    }
                    catch (IOException ex)
                    {
                        MessageBox.Show("Під час копіювання сталася помилка: " + ex.Message);
                    }
                }
            }
        }

        private void button10_Click_1(object sender, EventArgs e)
        {
            if (listBox2.SelectedItem != null)
            {
                string selectedItem = listBox2.SelectedItem.ToString();

                try
                {
                    FileAttributes attr = File.GetAttributes(selectedItem);

                    if ((attr & FileAttributes.Directory) == FileAttributes.Directory)
                    {
                        DirectoryInfo selectedDirectory = new DirectoryInfo(selectedItem);
                        if (selectedDirectory.Exists)
                        {
                            selectedDirectory.Delete(true);
                            MessageBox.Show("Директорія видалена.");
                        }
                    }
                    else
                    {
                        FileInfo selectedFile = new FileInfo(selectedItem);
                        if (selectedFile.Exists)
                        {
                            selectedFile.Delete();
                            MessageBox.Show("Файл видалено.");
                        }
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show($"Під час видалення сталася помилка: {ex.Message}");
                }
            }
            else
            {
                MessageBox.Show("Оберіть елемент для видалення.");
            }
        }

        private void button11_Click_1(object sender, EventArgs e)
        {
            if (listBox2.SelectedItem != null)
            {
                string selectedItem = listBox2.SelectedItem.ToString();

                try
                {
                    FileAttributes attr = File.GetAttributes(selectedItem);

                    if ((attr & FileAttributes.Directory) == FileAttributes.Directory)
                    {
                        DirectoryInfo selectedDirectory = new DirectoryInfo(selectedItem);
                        if (selectedDirectory.Exists)
                        {
                            FileAttributes newAttr = File.GetAttributes(selectedItem);
                            if ((newAttr & FileAttributes.ReadOnly) == FileAttributes.ReadOnly)
                                newAttr &= ~FileAttributes.ReadOnly;
                            else
                                newAttr |= FileAttributes.ReadOnly;

                            File.SetAttributes(selectedItem, newAttr);
                            MessageBox.Show("Атрибути категорії змінено.");
                        }
                    }
                    else
                    {
                        FileInfo selectedFile = new FileInfo(selectedItem);
                        if (selectedFile.Exists)
                        {
                            FileAttributes newAttr = selectedFile.Attributes;
                            if ((newAttr & FileAttributes.ReadOnly) == FileAttributes.ReadOnly)
                                newAttr &= ~FileAttributes.ReadOnly;
                            else
                                newAttr |= FileAttributes.ReadOnly;

                            File.SetAttributes(selectedItem, newAttr);
                            MessageBox.Show("Атрибути файлу змінено.");
                        }
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show($"Під час редагування атрибутів сталася помилка: {ex.Message}");
                }
            }
            else
            {
                MessageBox.Show("Оберіть елемент для редагування атрибутів.");
            }
        }

        private void button12_Click_1(object sender, EventArgs e)
        {
            if (listBox2.SelectedItem != null)
            {
                string selectedItem = listBox2.SelectedItem.ToString();

                if (File.Exists(selectedItem))
                {
                    try
                    {
                        string fileContent = File.ReadAllText(selectedItem);
                        using (Form editForm = new Form())
                        {
                            TextBox textBox = new TextBox
                            {
                                Multiline = true,
                                Dock = DockStyle.Fill,
                                Text = fileContent
                            };
                            editForm.Controls.Add(textBox);

                            Button saveButton = new Button
                            {
                                Text = "Зберегти",
                                Dock = DockStyle.Bottom
                            };
                            saveButton.Click += (s, args) =>
                            {
                                File.WriteAllText(selectedItem, textBox.Text);
                                editForm.Close();
                            };
                            editForm.Controls.Add(saveButton);

                            editForm.ShowDialog();
                        }
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show($"Під час редагування сталася помилка: {ex.Message}");
                    }
                }
                else
                {
                    MessageBox.Show("Файл не існує.");
                }
            }
            else
            {
                MessageBox.Show("Оберіть файл для редагування.");
            }
        }
    }
}
