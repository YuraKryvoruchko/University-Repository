using System;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using OOP_Lesson_24.Algoritms;

namespace OOP_Lesson_24
{
    public partial class Form1 : Form
    {
        private MD3Algoritm md3Hasher;
        private KhafreaAlgoritm khafreEncryptor;
        private OngSchnorrShamirAlgoritm ongEncryptor;

        private Thread thread1;
        private Thread thread2;
        private Thread thread3;

        public Form1()
        {
            InitializeComponent();
            khafreEncryptor = new KhafreaAlgoritm();
            md3Hasher = new MD3Algoritm();
            ongEncryptor = new OngSchnorrShamirAlgoritm();

            thread1 = new Thread(new ThreadStart(RunKHAFRE));
            thread2 = new Thread(new ThreadStart(RunMD3));
            thread3 = new Thread(new ThreadStart(RunONG));
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if(thread1.ThreadState == ThreadState.Unstarted)
                thread1.Start();
            else if(thread1.ThreadState == ThreadState.Suspended)
                thread1.Resume();
        }
        private void button2_Click_1(object sender, EventArgs e)
        {
            textBox2.Text = "Виконання зупинено.";
            thread1.Suspend();
        }
        private void RunKHAFRE()
        {
            try
            {
                textBox2.AppendText("Початок роботи KHAFRE..." + Environment.NewLine);
                Parallel.Invoke(() =>
                {
                    textBox2.AppendText("Початок роботи KHAFRE..." + Environment.NewLine);
                });
                Thread.Sleep(1000);

                byte[] plaintextBytes = Encoding.UTF8.GetBytes(textBox1.Text);

                byte[] ciphertext = khafreEncryptor.Encrypt(plaintextBytes);

                string ciphertextHex = BitConverter.ToString(ciphertext).Replace("-", "");

                Parallel.Invoke(() =>
                {
                    textBox2.Text = ciphertextHex;
                });
            }
            catch (Exception)
            {
                textBox2.Text = "Виконання зупинено.";
            }
        }

        private void button3_Click_1(object sender, EventArgs e)
        {
            if (thread2.ThreadState == ThreadState.Unstarted)
                thread2.Start();
            else if (thread1.ThreadState == ThreadState.Suspended)
                thread2.Resume();
        }
        private void button4_Click_1(object sender, EventArgs e)
        {
            textBox4.AppendText("Виконання зупинено." + Environment.NewLine);
            thread2.Suspend();
        }
        private void RunMD3()
        {
            try
            {
                Parallel.Invoke(() =>
                {
                    textBox4.AppendText("Початок роботи MD-3..." + Environment.NewLine);
                });

                Thread.Sleep(2000);

                byte[] inputBytes = Encoding.UTF8.GetBytes(textBox4.Text);

                byte[] hash = md3Hasher.Hash(inputBytes);

                string hashHex = BitConverter.ToString(hash).Replace("-", "");

                Parallel.Invoke(() =>
                {
                    textBox4.Text = hashHex;
                });
            }
            catch (Exception)
            {
                textBox4.AppendText("Виконання зупинено." + Environment.NewLine);
            }
        }

        private void button5_Click_1(object sender, EventArgs e)
        {
            if (thread3.ThreadState == ThreadState.Unstarted)
                thread3.Start();
            else if (thread1.ThreadState == ThreadState.Suspended)
                thread3.Resume();
        }
        private void button6_Click_1(object sender, EventArgs e)
        {
            textBox6.AppendText("Виконання зупинено." + Environment.NewLine);
            thread3.Suspend();
        }
        private void RunONG()
        {
            try
            {
                Parallel.Invoke(() =>
                {
                    textBox6.AppendText("Початок роботи ONG-Schnorr-Shamir..." + Environment.NewLine);
                });

                Thread.Sleep(3000);

                Parallel.Invoke(() =>
                {
                    textBox6.Text = ongEncryptor.Encrypt(textBox6.Text, ongEncryptor.N).ToString();
                });
            }
            catch (Exception)
            {
                textBox6.AppendText("Виконання зупинено." + Environment.NewLine);
            }
        }

        private void button7_Click_1(object sender, EventArgs e)
        {
            thread1.Start();
            thread2.Start();
            thread3.Start();
        }
        private void button8_Click_1(object sender, EventArgs e)
        {
            thread1.Suspend();
            thread2.Suspend();
            thread3.Suspend();
        }

        private void form1_FormClosed(object sender, FormClosedEventArgs e)
        {
            thread1.Abort();
            thread2.Abort();
            thread3.Abort();
        }
    }
}
