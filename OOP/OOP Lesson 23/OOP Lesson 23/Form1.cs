using System;
using System.Diagnostics;
using System.Drawing;
using System.Windows.Forms;

namespace OOP_Lesson_23
{
    public partial class Form1 : Form
    {
        private float _a = 1;

        public Form1()
        {
            this.InitializeComponent();
        }

        private void BtnDraw_Click(object sender, EventArgs e)
        {
            if (float.TryParse(txtA.Text, out float inputA))
            {
                if (inputA != 0)
                {
                    _a = inputA;
                    pictureBox.Invalidate();
                }
                else
                {
                    MessageBox.Show("Coefficients a must be non-zero.", "Invalid Input");
                }
            }
            else
            {
                MessageBox.Show("Please enter valid numbers for a.", "Invalid Input");
            }
        }

        private void PictureBox_Paint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;
            g.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.AntiAlias;

            int width = pictureBox.Width;
            int height = pictureBox.Height;
            int centerX = width / 2;
            int centerY = height / 2;
            float scale = 50f;

            using (Pen axisPen = new Pen(Color.Black, 2))
            {
                g.DrawLine(axisPen, 0, centerY, width, centerY);
                g.DrawLine(axisPen, centerX, 0, centerX, height);
            }

            using (Font font = new Font("Times New Roman", 8))
            using (Brush brush = new SolidBrush(Color.Black))
            {
                int xNumbers = (int)(width / scale) / 2;
                int yNumbers = (int)(height / scale) / 2;
                for (int i = -xNumbers; i <= xNumbers; i++)
                {
                    if (i != 0)
                    {
                        float x = centerX + i * scale;
                        g.DrawLine(Pens.Black, x, centerY - 5, x, centerY + 5);
                        g.DrawString(i.ToString(), font, brush, x - 5, centerY + 10);
                    }
                }

                for (int i = -yNumbers; i <= yNumbers; i++)
                {
                    if (i != 0)
                    {
                        float y = centerY - i * scale;
                        g.DrawLine(Pens.Black, centerX - 5, y, centerX + 5, y);
                        g.DrawString(i.ToString(), font, brush, centerX + 10, y - 5);
                    }
                }

                g.DrawString("X", font, brush, width - 20, centerY + 10);
                g.DrawString("Y", font, brush, centerX + 10, 10);
            }

            float[,] ranges = { { -70f, -1f }, { -1f, 70f } };
            float step = 0.01f;

            using (Pen curvePen = new Pen(Color.Blue, 2))
            {
                for (int rangeIndex = 0; rangeIndex < 2; rangeIndex++)
                {
                    float minT = ranges[rangeIndex, 0];
                    float maxT = ranges[rangeIndex, 1];
                    PointF[] points = new PointF[Convert.ToInt32((maxT - minT) / step) + 1];
                    int index = 0;

                    for (float t = minT; t < maxT; t += step)
                    {
                        float denominator = 1 + t * t * t;
                        if (Math.Abs(denominator) < 0.001f)
                            continue;

                        float x = (3 * _a * t) / denominator;
                        float y = (3 * _a * t * t) / denominator;

                        if (Math.Abs(x) <= 10 && Math.Abs(y) <= 5)
                        {
                            points[index] = new PointF(centerX + x * scale, centerY - y * scale);
                            index++;
                        }
                    }

                    if (index > 1)
                    {
                        PointF[] curvePoints = new PointF[index];
                        Array.Copy(points, 0, curvePoints, 0, index);
                        g.DrawCurve(curvePen, curvePoints);
                    }
                }
            }
        }
    }
}
