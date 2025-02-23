namespace OOP_Lesson_17
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
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
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.rbComplex = new System.Windows.Forms.RadioButton();
            this.rbRational = new System.Windows.Forms.RadioButton();
            this.lFirstPair = new System.Windows.Forms.Label();
            this.tbFirstPair1 = new System.Windows.Forms.TextBox();
            this.tbFirstPair2 = new System.Windows.Forms.TextBox();
            this.tbSecondPair2 = new System.Windows.Forms.TextBox();
            this.tbSecondPair1 = new System.Windows.Forms.TextBox();
            this.lSecondPair = new System.Windows.Forms.Label();
            this.lResult = new System.Windows.Forms.Label();
            this.tbResult = new System.Windows.Forms.TextBox();
            this.bAdd = new System.Windows.Forms.Button();
            this.bSubtract = new System.Windows.Forms.Button();
            this.bMultiply = new System.Windows.Forms.Button();
            this.bDivide = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // rbComplex
            // 
            this.rbComplex.AutoSize = true;
            this.rbComplex.Checked = true;
            this.rbComplex.Location = new System.Drawing.Point(12, 12);
            this.rbComplex.Name = "rbComplex";
            this.rbComplex.Size = new System.Drawing.Size(95, 24);
            this.rbComplex.TabIndex = 0;
            this.rbComplex.TabStop = true;
            this.rbComplex.Text = "Complex";
            this.rbComplex.UseVisualStyleBackColor = true;
            // 
            // rbRational
            // 
            this.rbRational.AutoSize = true;
            this.rbRational.Location = new System.Drawing.Point(113, 12);
            this.rbRational.Name = "rbRational";
            this.rbRational.Size = new System.Drawing.Size(93, 24);
            this.rbRational.TabIndex = 1;
            this.rbRational.TabStop = true;
            this.rbRational.Text = "Rational";
            this.rbRational.UseVisualStyleBackColor = true;
            // 
            // lFirstPair
            // 
            this.lFirstPair.AutoSize = true;
            this.lFirstPair.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.lFirstPair.Location = new System.Drawing.Point(13, 62);
            this.lFirstPair.Name = "lFirstPair";
            this.lFirstPair.Size = new System.Drawing.Size(185, 46);
            this.lFirstPair.TabIndex = 2;
            this.lFirstPair.Text = "First pair:";
            // 
            // tbFirstPair1
            // 
            this.tbFirstPair1.Location = new System.Drawing.Point(266, 80);
            this.tbFirstPair1.Name = "tbFirstPair1";
            this.tbFirstPair1.Size = new System.Drawing.Size(270, 26);
            this.tbFirstPair1.TabIndex = 3;
            // 
            // tbFirstPair2
            // 
            this.tbFirstPair2.Location = new System.Drawing.Point(542, 80);
            this.tbFirstPair2.Name = "tbFirstPair2";
            this.tbFirstPair2.Size = new System.Drawing.Size(322, 26);
            this.tbFirstPair2.TabIndex = 4;
            // 
            // tbSecondPair2
            // 
            this.tbSecondPair2.Location = new System.Drawing.Point(542, 136);
            this.tbSecondPair2.Name = "tbSecondPair2";
            this.tbSecondPair2.Size = new System.Drawing.Size(322, 26);
            this.tbSecondPair2.TabIndex = 7;
            // 
            // tbSecondPair1
            // 
            this.tbSecondPair1.Location = new System.Drawing.Point(266, 136);
            this.tbSecondPair1.Name = "tbSecondPair1";
            this.tbSecondPair1.Size = new System.Drawing.Size(270, 26);
            this.tbSecondPair1.TabIndex = 6;
            // 
            // lSecondPair
            // 
            this.lSecondPair.AutoSize = true;
            this.lSecondPair.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.lSecondPair.Location = new System.Drawing.Point(13, 118);
            this.lSecondPair.Name = "lSecondPair";
            this.lSecondPair.Size = new System.Drawing.Size(245, 46);
            this.lSecondPair.TabIndex = 5;
            this.lSecondPair.Text = "Second pair:";
            // 
            // lResult
            // 
            this.lResult.AutoSize = true;
            this.lResult.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.lResult.Location = new System.Drawing.Point(13, 240);
            this.lResult.Name = "lResult";
            this.lResult.Size = new System.Drawing.Size(144, 46);
            this.lResult.TabIndex = 8;
            this.lResult.Text = "Result:";
            // 
            // tbResult
            // 
            this.tbResult.Location = new System.Drawing.Point(169, 258);
            this.tbResult.Name = "tbResult";
            this.tbResult.Size = new System.Drawing.Size(695, 26);
            this.tbResult.TabIndex = 9;
            // 
            // bAdd
            // 
            this.bAdd.Location = new System.Drawing.Point(12, 183);
            this.bAdd.Name = "bAdd";
            this.bAdd.Size = new System.Drawing.Size(194, 45);
            this.bAdd.TabIndex = 10;
            this.bAdd.Text = "Add";
            this.bAdd.UseVisualStyleBackColor = true;
            this.bAdd.Click += new System.EventHandler(this.bAdd_Click);
            // 
            // bSubtract
            // 
            this.bSubtract.Location = new System.Drawing.Point(212, 183);
            this.bSubtract.Name = "bSubtract";
            this.bSubtract.Size = new System.Drawing.Size(212, 45);
            this.bSubtract.TabIndex = 11;
            this.bSubtract.Text = "Subtract";
            this.bSubtract.UseVisualStyleBackColor = true;
            this.bSubtract.Click += new System.EventHandler(this.bSubtract_Click);
            // 
            // bMultiply
            // 
            this.bMultiply.Location = new System.Drawing.Point(430, 183);
            this.bMultiply.Name = "bMultiply";
            this.bMultiply.Size = new System.Drawing.Size(212, 45);
            this.bMultiply.TabIndex = 12;
            this.bMultiply.Text = "Multiply";
            this.bMultiply.UseVisualStyleBackColor = true;
            this.bMultiply.Click += new System.EventHandler(this.bMultiply_Click);
            // 
            // bDivide
            // 
            this.bDivide.Location = new System.Drawing.Point(648, 183);
            this.bDivide.Name = "bDivide";
            this.bDivide.Size = new System.Drawing.Size(216, 45);
            this.bDivide.TabIndex = 13;
            this.bDivide.Text = "Divide";
            this.bDivide.UseVisualStyleBackColor = true;
            this.bDivide.Click += new System.EventHandler(this.bDivide_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(876, 320);
            this.Controls.Add(this.bDivide);
            this.Controls.Add(this.bMultiply);
            this.Controls.Add(this.bSubtract);
            this.Controls.Add(this.bAdd);
            this.Controls.Add(this.tbResult);
            this.Controls.Add(this.lResult);
            this.Controls.Add(this.tbSecondPair2);
            this.Controls.Add(this.tbSecondPair1);
            this.Controls.Add(this.lSecondPair);
            this.Controls.Add(this.tbFirstPair2);
            this.Controls.Add(this.tbFirstPair1);
            this.Controls.Add(this.lFirstPair);
            this.Controls.Add(this.rbRational);
            this.Controls.Add(this.rbComplex);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.RadioButton rbComplex;
        private System.Windows.Forms.RadioButton rbRational;
        private System.Windows.Forms.Label lFirstPair;
        private System.Windows.Forms.TextBox tbFirstPair1;
        private System.Windows.Forms.TextBox tbFirstPair2;
        private System.Windows.Forms.TextBox tbSecondPair2;
        private System.Windows.Forms.TextBox tbSecondPair1;
        private System.Windows.Forms.Label lSecondPair;
        private System.Windows.Forms.Label lResult;
        private System.Windows.Forms.TextBox tbResult;
        private System.Windows.Forms.Button bAdd;
        private System.Windows.Forms.Button bSubtract;
        private System.Windows.Forms.Button bMultiply;
        private System.Windows.Forms.Button bDivide;
    }
}

