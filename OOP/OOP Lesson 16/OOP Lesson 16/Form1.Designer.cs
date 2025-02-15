namespace OOP_Lesson_16
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            label1 = new Label();
            label2 = new Label();
            label4 = new Label();
            label5 = new Label();
            textBoxFirstPolynomial = new TextBox();
            textBoxSecondPolynomial = new TextBox();
            buttonAdd = new Button();
            buttonSubtract = new Button();
            buttonMultiply = new Button();
            textBoxResultPolynomial = new TextBox();
            textBoxXValue = new TextBox();
            buttonEvaluateFromResult = new Button();
            label3 = new Label();
            buttonEvaluateSecond = new Button();
            buttonEvaluateFirst = new Button();
            label6 = new Label();
            textBoxResultOfEvaluating = new TextBox();
            SuspendLayout();
            // 
            // label1
            // 
            label1.Font = new Font("Segoe UI", 14F);
            label1.Location = new Point(12, 9);
            label1.Name = "label1";
            label1.Size = new Size(286, 69);
            label1.TabIndex = 0;
            label1.Text = "First polynomial:";
            label1.TextAlign = ContentAlignment.MiddleRight;
            // 
            // label2
            // 
            label2.Font = new Font("Segoe UI", 14F);
            label2.Location = new Point(12, 78);
            label2.Name = "label2";
            label2.Size = new Size(286, 69);
            label2.TabIndex = 1;
            label2.Text = "Second polynomial:";
            label2.TextAlign = ContentAlignment.MiddleRight;
            // 
            // label4
            // 
            label4.Font = new Font("Segoe UI", 14F);
            label4.Location = new Point(12, 216);
            label4.Name = "label4";
            label4.Size = new Size(286, 69);
            label4.TabIndex = 3;
            label4.Text = "Result of:";
            label4.TextAlign = ContentAlignment.MiddleRight;
            // 
            // label5
            // 
            label5.Font = new Font("Segoe UI", 14F);
            label5.Location = new Point(12, 285);
            label5.Name = "label5";
            label5.Size = new Size(42, 69);
            label5.TabIndex = 4;
            label5.Text = "X:";
            label5.TextAlign = ContentAlignment.MiddleRight;
            // 
            // textBoxFirstPolynomial
            // 
            textBoxFirstPolynomial.Location = new Point(299, 32);
            textBoxFirstPolynomial.Name = "textBoxFirstPolynomial";
            textBoxFirstPolynomial.Size = new Size(489, 31);
            textBoxFirstPolynomial.TabIndex = 5;
            // 
            // textBoxSecondPolynomial
            // 
            textBoxSecondPolynomial.Location = new Point(299, 101);
            textBoxSecondPolynomial.Name = "textBoxSecondPolynomial";
            textBoxSecondPolynomial.Size = new Size(489, 31);
            textBoxSecondPolynomial.TabIndex = 6;
            // 
            // buttonAdd
            // 
            buttonAdd.Location = new Point(12, 161);
            buttonAdd.Name = "buttonAdd";
            buttonAdd.Size = new Size(250, 52);
            buttonAdd.TabIndex = 7;
            buttonAdd.Text = "Add";
            buttonAdd.UseVisualStyleBackColor = true;
            buttonAdd.Click += buttonAdd_Click;
            // 
            // buttonSubtract
            // 
            buttonSubtract.Location = new Point(275, 161);
            buttonSubtract.Name = "buttonSubtract";
            buttonSubtract.Size = new Size(250, 52);
            buttonSubtract.TabIndex = 8;
            buttonSubtract.Text = "Subtract";
            buttonSubtract.UseVisualStyleBackColor = true;
            buttonSubtract.Click += buttonSubtract_Click;
            // 
            // buttonMultiply
            // 
            buttonMultiply.Location = new Point(538, 161);
            buttonMultiply.Name = "buttonMultiply";
            buttonMultiply.Size = new Size(250, 52);
            buttonMultiply.TabIndex = 9;
            buttonMultiply.Text = "Multiply";
            buttonMultiply.UseVisualStyleBackColor = true;
            buttonMultiply.Click += buttonMultiply_Click;
            // 
            // textBoxResultPolynomial
            // 
            textBoxResultPolynomial.Location = new Point(299, 239);
            textBoxResultPolynomial.Name = "textBoxResultPolynomial";
            textBoxResultPolynomial.ReadOnly = true;
            textBoxResultPolynomial.Size = new Size(489, 31);
            textBoxResultPolynomial.TabIndex = 10;
            // 
            // textBoxXValue
            // 
            textBoxXValue.Location = new Point(60, 308);
            textBoxXValue.Name = "textBoxXValue";
            textBoxXValue.Size = new Size(115, 31);
            textBoxXValue.TabIndex = 11;
            textBoxXValue.Text = "0,0";
            // 
            // buttonEvaluateFromResult
            // 
            buttonEvaluateFromResult.Location = new Point(653, 297);
            buttonEvaluateFromResult.Name = "buttonEvaluateFromResult";
            buttonEvaluateFromResult.Size = new Size(135, 52);
            buttonEvaluateFromResult.TabIndex = 12;
            buttonEvaluateFromResult.Text = "From result";
            buttonEvaluateFromResult.UseVisualStyleBackColor = true;
            buttonEvaluateFromResult.Click += buttonEvaluateFromResult_Click;
            // 
            // label3
            // 
            label3.Font = new Font("Segoe UI", 14F);
            label3.Location = new Point(181, 285);
            label3.Name = "label3";
            label3.Size = new Size(170, 69);
            label3.TabIndex = 13;
            label3.Text = "Evaluate for";
            label3.TextAlign = ContentAlignment.MiddleRight;
            // 
            // buttonEvaluateSecond
            // 
            buttonEvaluateSecond.Location = new Point(506, 297);
            buttonEvaluateSecond.Name = "buttonEvaluateSecond";
            buttonEvaluateSecond.Size = new Size(135, 52);
            buttonEvaluateSecond.TabIndex = 14;
            buttonEvaluateSecond.Text = "Second";
            buttonEvaluateSecond.UseVisualStyleBackColor = true;
            buttonEvaluateSecond.Click += buttonEvaluateSecond_Click;
            // 
            // buttonEvaluateFirst
            // 
            buttonEvaluateFirst.Location = new Point(357, 297);
            buttonEvaluateFirst.Name = "buttonEvaluateFirst";
            buttonEvaluateFirst.Size = new Size(135, 52);
            buttonEvaluateFirst.TabIndex = 15;
            buttonEvaluateFirst.Text = "First";
            buttonEvaluateFirst.UseVisualStyleBackColor = true;
            buttonEvaluateFirst.Click += buttonEvaluateFirst_Click;
            // 
            // label6
            // 
            label6.Font = new Font("Segoe UI", 14F);
            label6.Location = new Point(12, 357);
            label6.Name = "label6";
            label6.Size = new Size(286, 69);
            label6.TabIndex = 16;
            label6.Text = "Result of evaluating:";
            label6.TextAlign = ContentAlignment.MiddleRight;
            // 
            // textBoxResultOfEvaluating
            // 
            textBoxResultOfEvaluating.Location = new Point(299, 380);
            textBoxResultOfEvaluating.Name = "textBoxResultOfEvaluating";
            textBoxResultOfEvaluating.ReadOnly = true;
            textBoxResultOfEvaluating.Size = new Size(489, 31);
            textBoxResultOfEvaluating.TabIndex = 17;
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(10F, 25F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 431);
            Controls.Add(textBoxResultOfEvaluating);
            Controls.Add(label6);
            Controls.Add(buttonEvaluateFirst);
            Controls.Add(buttonEvaluateSecond);
            Controls.Add(label3);
            Controls.Add(buttonEvaluateFromResult);
            Controls.Add(textBoxXValue);
            Controls.Add(textBoxResultPolynomial);
            Controls.Add(buttonMultiply);
            Controls.Add(buttonSubtract);
            Controls.Add(buttonAdd);
            Controls.Add(textBoxSecondPolynomial);
            Controls.Add(textBoxFirstPolynomial);
            Controls.Add(label5);
            Controls.Add(label4);
            Controls.Add(label2);
            Controls.Add(label1);
            Name = "Form1";
            Text = "205-ТН Лб16 Криворучко Ю.В.";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Label label1;
        private Label label2;
        private Label label4;
        private Label label5;
        private TextBox textBoxFirstPolynomial;
        private TextBox textBoxSecondPolynomial;
        private Button buttonAdd;
        private Button buttonSubtract;
        private Button buttonMultiply;
        private TextBox textBoxResultPolynomial;
        private TextBox textBoxXValue;
        private Button buttonEvaluateFromResult;
        private Label label3;
        private Button buttonEvaluateSecond;
        private Button buttonEvaluateFirst;
        private Label label6;
        private TextBox textBoxResultOfEvaluating;
    }
}
