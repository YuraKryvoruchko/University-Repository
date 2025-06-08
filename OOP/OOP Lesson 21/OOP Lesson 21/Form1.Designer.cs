using System.Drawing;
using System.Windows.Forms;

namespace OOP_Lesson_21
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            menuStrip1 = new MenuStrip();
            fileToolStripMenuItem = new ToolStripMenuItem();
            createToolStripMenuItem = new ToolStripMenuItem();
            openToolStripMenuItem = new ToolStripMenuItem();
            saveToolStripMenuItem = new ToolStripMenuItem();
            saveAsToolStripMenuItem = new ToolStripMenuItem();
            exitToolStripMenuItem = new ToolStripMenuItem();
            editToolStripMenuItem = new ToolStripMenuItem();
            cutToolStripMenuItem = new ToolStripMenuItem();
            copyToolStripMenuItem = new ToolStripMenuItem();
            pasteToolStripMenuItem = new ToolStripMenuItem();
            selectAllВсеToolStripMenuItem = new ToolStripMenuItem();
            deleteToolStripMenuItem = new ToolStripMenuItem();
            paste1ToolStripMenuItem = new ToolStripMenuItem();
            selectImageToolStripMenuItem = new ToolStripMenuItem();
            toolStripMenuItem1 = new ToolStripMenuItem();
            toolStripMenuItem2 = new ToolStripMenuItem();
            fontColorToolStripMenuItem = new ToolStripMenuItem();
            alignmentToolStripMenuItem = new ToolStripMenuItem();
            leftToolStripMenuItem = new ToolStripMenuItem();
            centerToolStripMenuItem = new ToolStripMenuItem();
            rightToolStripMenuItem = new ToolStripMenuItem();
            windowToolStripMenuItem = new ToolStripMenuItem();
            arrangeIconsToolStripMenuItem = new ToolStripMenuItem();
            cascadeToolStripMenuItem = new ToolStripMenuItem();
            horizontalToolStripMenuItem = new ToolStripMenuItem();
            verticalToolStripMenuItem = new ToolStripMenuItem();
            languagesToolStripMenuItem = new ToolStripMenuItem();
            ukraineToolStripMenuItem = new ToolStripMenuItem();
            englishToolStripMenuItem = new ToolStripMenuItem();
            toolStrip1 = new ToolStrip();
            toolStripButton1 = new ToolStripButton();
            toolStripButton2 = new ToolStripButton();
            toolStripButton3 = new ToolStripButton();
            toolStripButton4 = new ToolStripButton();
            toolStripButton5 = new ToolStripButton();
            toolStripButton6 = new ToolStripButton();
            toolStripButton7 = new ToolStripButton();
            toolStripButton8 = new ToolStripButton();
            menuStrip1.SuspendLayout();
            toolStrip1.SuspendLayout();
            SuspendLayout();
            // 
            // menuStrip1
            // 
            menuStrip1.BackColor = SystemColors.ControlLightLight;
            menuStrip1.ImageScalingSize = new Size(24, 24);
            menuStrip1.Items.AddRange(new ToolStripItem[] { fileToolStripMenuItem, editToolStripMenuItem, paste1ToolStripMenuItem, toolStripMenuItem1, windowToolStripMenuItem, languagesToolStripMenuItem });
            menuStrip1.Location = new Point(0, 0);
            menuStrip1.Name = "menuStrip1";
            menuStrip1.Size = new Size(1393, 39);
            menuStrip1.TabIndex = 0;
            menuStrip1.Text = "menuStrip1";
            // 
            // fileToolStripMenuItem
            // 
            fileToolStripMenuItem.DropDownItems.AddRange(new ToolStripItem[] { createToolStripMenuItem, openToolStripMenuItem, saveToolStripMenuItem, saveAsToolStripMenuItem, exitToolStripMenuItem });
            fileToolStripMenuItem.Font = new Font("ISOCPEUR", 12F, FontStyle.Regular, GraphicsUnit.Point, 204);
            fileToolStripMenuItem.ForeColor = Color.Brown;
            fileToolStripMenuItem.Name = "fileToolStripMenuItem";
            fileToolStripMenuItem.Size = new Size(81, 35);
            fileToolStripMenuItem.Text = "Файл";
            // 
            // створитиToolStripMenuItem
            // 
            createToolStripMenuItem.ForeColor = Color.Brown;
            createToolStripMenuItem.Name = "createToolStripMenuItem";
            createToolStripMenuItem.Size = new Size(257, 40);
            createToolStripMenuItem.Text = "Створити";
            createToolStripMenuItem.Click += createToolStripMenuItem_Click;
            // 
            // openToolStripMenuItem
            // 
            openToolStripMenuItem.ForeColor = Color.Brown;
            openToolStripMenuItem.Name = "openToolStripMenuItem";
            openToolStripMenuItem.Size = new Size(257, 40);
            openToolStripMenuItem.Text = "Відкрити";
            openToolStripMenuItem.Click += openToolStripMenuItem_Click;
            // 
            // saveToolStripMenuItem
            // 
            saveToolStripMenuItem.ForeColor = Color.Brown;
            saveToolStripMenuItem.Name = "saveToolStripMenuItem";
            saveToolStripMenuItem.Size = new Size(257, 40);
            saveToolStripMenuItem.Text = "Зберегти";
            saveToolStripMenuItem.Click += saveToolStripMenuItem_Click;
            // 
            // saveAsToolStripMenuItem
            // 
            saveAsToolStripMenuItem.ForeColor = Color.Brown;
            saveAsToolStripMenuItem.Name = "saveAsToolStripMenuItem";
            saveAsToolStripMenuItem.Size = new Size(257, 40);
            saveAsToolStripMenuItem.Text = "Зберегти як...";
            saveAsToolStripMenuItem.Click += saveAsToolStripMenuItem_Click;
            // 
            // exitToolStripMenuItem
            // 
            exitToolStripMenuItem.ForeColor = Color.Brown;
            exitToolStripMenuItem.Name = "exitToolStripMenuItem";
            exitToolStripMenuItem.Size = new Size(257, 40);
            exitToolStripMenuItem.Text = "Вийти";
            exitToolStripMenuItem.Click += exitToolStripMenuItem_Click;
            // 
            // editToolStripMenuItem
            // 
            editToolStripMenuItem.DropDownItems.AddRange(new ToolStripItem[] { cutToolStripMenuItem, copyToolStripMenuItem, pasteToolStripMenuItem, selectAllВсеToolStripMenuItem, deleteToolStripMenuItem });
            editToolStripMenuItem.Font = new Font("ISOCPEUR", 12F, FontStyle.Regular, GraphicsUnit.Point, 204);
            editToolStripMenuItem.ForeColor = Color.Brown;
            editToolStripMenuItem.Name = "editToolStripMenuItem";
            editToolStripMenuItem.Size = new Size(155, 35);
            editToolStripMenuItem.Text = "Редагування";
            // 
            // cutToolStripMenuItem
            // 
            cutToolStripMenuItem.ForeColor = Color.Brown;
            cutToolStripMenuItem.Name = "cutToolStripMenuItem";
            cutToolStripMenuItem.Size = new Size(246, 40);
            cutToolStripMenuItem.Text = "Вирізати";
            cutToolStripMenuItem.Click += cutToolStripMenuItem_Click;
            // 
            // copyToolStripMenuItem
            // 
            copyToolStripMenuItem.ForeColor = Color.Brown;
            copyToolStripMenuItem.Name = "copyToolStripMenuItem";
            copyToolStripMenuItem.Size = new Size(246, 40);
            copyToolStripMenuItem.Text = "Копіювати";
            copyToolStripMenuItem.Click += copyToolStripMenuItem_Click;
            // 
            // pasteToolStripMenuItem
            // 
            pasteToolStripMenuItem.ForeColor = Color.Brown;
            pasteToolStripMenuItem.Name = "pasteToolStripMenuItem";
            pasteToolStripMenuItem.Size = new Size(246, 40);
            pasteToolStripMenuItem.Text = "Вставити";
            pasteToolStripMenuItem.Click += pasteToolStripMenuItem_Click;
            // 
            // selectAllВсеToolStripMenuItem
            // 
            selectAllВсеToolStripMenuItem.ForeColor = Color.Brown;
            selectAllВсеToolStripMenuItem.Name = "selectAllВсеToolStripMenuItem";
            selectAllВсеToolStripMenuItem.Size = new Size(246, 40);
            selectAllВсеToolStripMenuItem.Text = "Виділити все";
            selectAllВсеToolStripMenuItem.Click += selectAllToolStripMenuItem_Click;
            // 
            // deleteToolStripMenuItem
            // 
            deleteToolStripMenuItem.ForeColor = Color.Brown;
            deleteToolStripMenuItem.Name = "deleteToolStripMenuItem";
            deleteToolStripMenuItem.Size = new Size(246, 40);
            deleteToolStripMenuItem.Text = "Видалити";
            deleteToolStripMenuItem.Click += deleteToolStripMenuItem_Click;
            // 
            // paste1ToolStripMenuItem
            // 
            paste1ToolStripMenuItem.DropDownItems.AddRange(new ToolStripItem[] { selectImageToolStripMenuItem });
            paste1ToolStripMenuItem.Font = new Font("ISOCPEUR", 12F, FontStyle.Regular, GraphicsUnit.Point, 204);
            paste1ToolStripMenuItem.ForeColor = Color.Firebrick;
            paste1ToolStripMenuItem.Name = "paste1ToolStripMenuItem";
            paste1ToolStripMenuItem.Size = new Size(112, 35);
            paste1ToolStripMenuItem.Text = "Вставка";
            // 
            // selectImageToolStripMenuItem
            // 
            selectImageToolStripMenuItem.ForeColor = Color.Brown;
            selectImageToolStripMenuItem.Name = "selectImageToolStripMenuItem";
            selectImageToolStripMenuItem.Size = new Size(313, 40);
            selectImageToolStripMenuItem.Text = "Обрати зображення";
            selectImageToolStripMenuItem.Click += selectImageToolStripMenuItem_Click;
            // 
            // toolStripMenuItem1
            // 
            toolStripMenuItem1.DropDownItems.AddRange(new ToolStripItem[] { toolStripMenuItem2, fontColorToolStripMenuItem, alignmentToolStripMenuItem });
            toolStripMenuItem1.Font = new Font("ISOCPEUR", 12F, FontStyle.Regular, GraphicsUnit.Point, 204);
            toolStripMenuItem1.ForeColor = Color.Firebrick;
            toolStripMenuItem1.Name = "toolStripMenuItem1";
            toolStripMenuItem1.Size = new Size(106, 35);
            toolStripMenuItem1.Text = "Формат";
            // 
            // toolStripMenuItem2
            // 
            toolStripMenuItem2.ForeColor = Color.Brown;
            toolStripMenuItem2.Name = "toolStripMenuItem2";
            toolStripMenuItem2.Size = new Size(253, 40);
            toolStripMenuItem2.Text = "Шрифт";
            toolStripMenuItem2.Click += toolStripMenuItem2_Click;
            // 
            // fontColorToolStripMenuItem
            // 
            fontColorToolStripMenuItem.ForeColor = Color.Brown;
            fontColorToolStripMenuItem.Name = "fontColorToolStripMenuItem";
            fontColorToolStripMenuItem.Size = new Size(253, 40);
            fontColorToolStripMenuItem.Text = "Колір шрифту";
            fontColorToolStripMenuItem.Click += fontColorToolStripMenuItem_Click;
            // 
            // alignmentToolStripMenuItem
            // 
            alignmentToolStripMenuItem.DropDownItems.AddRange(new ToolStripItem[] { leftToolStripMenuItem, centerToolStripMenuItem, rightToolStripMenuItem });
            alignmentToolStripMenuItem.ForeColor = Color.Brown;
            alignmentToolStripMenuItem.Name = "alignmentToolStripMenuItem";
            alignmentToolStripMenuItem.Size = new Size(253, 40);
            alignmentToolStripMenuItem.Text = "Вирівнювання";
            // 
            // leftToolStripMenuItem
            // 
            leftToolStripMenuItem.ForeColor = Color.Brown;
            leftToolStripMenuItem.Name = "leftToolStripMenuItem";
            leftToolStripMenuItem.Size = new Size(220, 40);
            leftToolStripMenuItem.Text = "Ліве";
            leftToolStripMenuItem.Click += leftToolStripMenuItem_Click;
            // 
            // centerToolStripMenuItem
            // 
            centerToolStripMenuItem.ForeColor = Color.Brown;
            centerToolStripMenuItem.Name = "centerToolStripMenuItem";
            centerToolStripMenuItem.Size = new Size(220, 40);
            centerToolStripMenuItem.Text = "По центру";
            centerToolStripMenuItem.Click += centerToolStripMenuItem_Click;
            // 
            // rightToolStripMenuItem
            // 
            rightToolStripMenuItem.ForeColor = Color.Brown;
            rightToolStripMenuItem.Name = "rightToolStripMenuItem";
            rightToolStripMenuItem.Size = new Size(220, 40);
            rightToolStripMenuItem.Text = "Праве";
            rightToolStripMenuItem.Click += rightToolStripMenuItem_Click;
            // 
            // windowToolStripMenuItem
            // 
            windowToolStripMenuItem.DropDownItems.AddRange(new ToolStripItem[] { arrangeIconsToolStripMenuItem, cascadeToolStripMenuItem, horizontalToolStripMenuItem, verticalToolStripMenuItem });
            windowToolStripMenuItem.Font = new Font("ISOCPEUR", 12F, FontStyle.Regular, GraphicsUnit.Point, 204);
            windowToolStripMenuItem.ForeColor = Color.Brown;
            windowToolStripMenuItem.Name = "windowToolStripMenuItem";
            windowToolStripMenuItem.Size = new Size(81, 35);
            windowToolStripMenuItem.Text = "Вікно";
            // 
            // arrangeIconsToolStripMenuItem
            // 
            arrangeIconsToolStripMenuItem.ForeColor = Color.Brown;
            arrangeIconsToolStripMenuItem.Name = "arrangeIconsToolStripMenuItem";
            arrangeIconsToolStripMenuItem.Size = new Size(416, 40);
            arrangeIconsToolStripMenuItem.Text = "Впорядкувати вікна";
            arrangeIconsToolStripMenuItem.Click += arrangeIconsToolStripMenuItem_Click;
            // 
            // cascadeToolStripMenuItem
            // 
            cascadeToolStripMenuItem.ForeColor = Color.Brown;
            cascadeToolStripMenuItem.Name = "cascadeToolStripMenuItem";
            cascadeToolStripMenuItem.Size = new Size(416, 40);
            cascadeToolStripMenuItem.Text = "Каскадне розташування";
            cascadeToolStripMenuItem.Click += cascadeToolStripMenuItem_Click;
            // 
            // horizontalToolStripMenuItem
            // 
            horizontalToolStripMenuItem.ForeColor = Color.Brown;
            horizontalToolStripMenuItem.Name = "horizontalToolStripMenuItem";
            horizontalToolStripMenuItem.Size = new Size(416, 40);
            horizontalToolStripMenuItem.Text = "Горизонтальне розташування";
            horizontalToolStripMenuItem.Click += horizontalToolStripMenuItem_Click;
            // 
            // verticalToolStripMenuItem
            // 
            verticalToolStripMenuItem.ForeColor = Color.Brown;
            verticalToolStripMenuItem.Name = "verticalToolStripMenuItem";
            verticalToolStripMenuItem.Size = new Size(416, 40);
            verticalToolStripMenuItem.Text = "Вертикальне розташування";
            verticalToolStripMenuItem.Click += verticalToolStripMenuItem_Click;
            // 
            // languagesToolStripMenuItem
            // 
            languagesToolStripMenuItem.DropDownItems.AddRange(new ToolStripItem[] { ukraineToolStripMenuItem, englishToolStripMenuItem });
            languagesToolStripMenuItem.Font = new Font("ISOCPEUR", 12F, FontStyle.Regular, GraphicsUnit.Point, 204);
            languagesToolStripMenuItem.ForeColor = Color.Brown;
            languagesToolStripMenuItem.Name = "languagesToolStripMenuItem";
            languagesToolStripMenuItem.Size = new Size(78, 35);
            languagesToolStripMenuItem.Text = "Мова";
            // 
            // ukraineToolStripMenuItem
            // 
            ukraineToolStripMenuItem.ForeColor = Color.Brown;
            ukraineToolStripMenuItem.Name = "ukraineToolStripMenuItem";
            ukraineToolStripMenuItem.Size = new Size(228, 40);
            ukraineToolStripMenuItem.Text = "Українська";
            ukraineToolStripMenuItem.Click += ukraineToolStripMenuItem_Click;
            // 
            // englishToolStripMenuItem
            // 
            englishToolStripMenuItem.ForeColor = Color.Brown;
            englishToolStripMenuItem.Name = "englishToolStripMenuItem";
            englishToolStripMenuItem.Size = new Size(228, 40);
            englishToolStripMenuItem.Text = "English";
            englishToolStripMenuItem.Click += englishToolStripMenuItem_Click;
            // 
            // toolStrip1
            // 
            toolStrip1.BackColor = Color.Brown;
            toolStrip1.Dock = DockStyle.Left;
            toolStrip1.ImageScalingSize = new Size(24, 24);
            toolStrip1.Items.AddRange(new ToolStripItem[] { toolStripButton1, toolStripButton2, toolStripButton3, toolStripButton4, toolStripButton5, toolStripButton6, toolStripButton7, toolStripButton8 });
            toolStrip1.Location = new Point(0, 39);
            toolStrip1.Name = "toolStrip1";
            toolStrip1.Size = new Size(36, 864);
            toolStrip1.TabIndex = 2;
            toolStrip1.Text = "toolStrip1";
            // 
            // toolStripButton1
            // 
            toolStripButton1.DisplayStyle = ToolStripItemDisplayStyle.Image;
            toolStripButton1.ImageTransparentColor = Color.Magenta;
            toolStripButton1.Name = "toolStripButton1";
            toolStripButton1.Size = new Size(31, 28);
            toolStripButton1.Text = "Створити новий документ";
            toolStripButton1.Click += toolStripButton1_Click;
            // 
            // toolStripButton2
            // 
            toolStripButton2.DisplayStyle = ToolStripItemDisplayStyle.Image;
            toolStripButton2.ImageTransparentColor = Color.Magenta;
            toolStripButton2.Name = "toolStripButton2";
            toolStripButton2.Size = new Size(31, 28);
            toolStripButton2.Text = "Зберегти документ";
            toolStripButton2.Click += toolStripButton2_Click;
            // 
            // toolStripButton3
            // 
            toolStripButton3.DisplayStyle = ToolStripItemDisplayStyle.Image;
            toolStripButton3.ImageTransparentColor = Color.Magenta;
            toolStripButton3.Name = "toolStripButton3";
            toolStripButton3.Size = new Size(31, 28);
            toolStripButton3.Text = "Копіювання тексту";
            toolStripButton3.Click += toolStripButton3_Click;
            // 
            // toolStripButton4
            // 
            toolStripButton4.DisplayStyle = ToolStripItemDisplayStyle.Image;
            toolStripButton4.ImageTransparentColor = Color.Magenta;
            toolStripButton4.Name = "toolStripButton4";
            toolStripButton4.Size = new Size(31, 28);
            toolStripButton4.Text = "Вставка тексту";
            toolStripButton4.Click += toolStripButton4_Click;
            // 
            // toolStripButton5
            // 
            toolStripButton5.DisplayStyle = ToolStripItemDisplayStyle.Image;
            toolStripButton5.ImageTransparentColor = Color.Magenta;
            toolStripButton5.Name = "toolStripButton5";
            toolStripButton5.Size = new Size(31, 28);
            toolStripButton5.Text = "Вставка зображення";
            toolStripButton5.Click += toolStripButton5_Click;
            // 
            // toolStripButton6
            // 
            toolStripButton6.DisplayStyle = ToolStripItemDisplayStyle.Image;
            toolStripButton6.ImageTransparentColor = Color.Magenta;
            toolStripButton6.Name = "toolStripButton6";
            toolStripButton6.Size = new Size(31, 28);
            toolStripButton6.Text = "Жирний текст";
            toolStripButton6.Click += toolStripButton6_Click;
            // 
            // toolStripButton7
            // 
            toolStripButton7.DisplayStyle = ToolStripItemDisplayStyle.Image;
            toolStripButton7.ImageTransparentColor = Color.Magenta;
            toolStripButton7.Name = "toolStripButton7";
            toolStripButton7.Size = new Size(31, 28);
            toolStripButton7.Text = "Курсив";
            toolStripButton7.Click += toolStripButton7_Click;
            // 
            // toolStripButton8
            // 
            toolStripButton8.DisplayStyle = ToolStripItemDisplayStyle.Image;
            toolStripButton8.ImageTransparentColor = Color.Magenta;
            toolStripButton8.Name = "toolStripButton8";
            toolStripButton8.Size = new Size(31, 28);
            toolStripButton8.Text = "Підкреслений текст";
            toolStripButton8.Click += toolStripButton8_Click;
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(10F, 25F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = SystemColors.Control;
            ClientSize = new Size(1393, 903);
            Controls.Add(toolStrip1);
            Controls.Add(menuStrip1);
            ForeColor = Color.Brown;
            IsMdiContainer = true;
            MainMenuStrip = menuStrip1;
            Name = "Form1";
            ShowIcon = false;
            Text = "205-ТН Лб21 Криворучко Ю.В.";
            menuStrip1.ResumeLayout(false);
            menuStrip1.PerformLayout();
            toolStrip1.ResumeLayout(false);
            toolStrip1.PerformLayout();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private MenuStrip menuStrip1;
        private ToolStripMenuItem fileToolStripMenuItem;
        private ToolStripMenuItem createToolStripMenuItem;
        private ToolStripMenuItem openToolStripMenuItem;
        private ToolStripMenuItem saveToolStripMenuItem;
        private ToolStripMenuItem saveAsToolStripMenuItem;
        private ToolStripMenuItem exitToolStripMenuItem;
        private ToolStripMenuItem editToolStripMenuItem;
        private ToolStripMenuItem cutToolStripMenuItem;
        private ToolStripMenuItem copyToolStripMenuItem;
        private ToolStripMenuItem pasteToolStripMenuItem;
        private ToolStripMenuItem selectAllВсеToolStripMenuItem;
        private ToolStripMenuItem deleteToolStripMenuItem;
        private ToolStripMenuItem paste1ToolStripMenuItem;
        private ToolStripMenuItem selectImageToolStripMenuItem;
        private ToolStripMenuItem toolStripMenuItem1;
        private ToolStripMenuItem toolStripMenuItem2;
        private ToolStripMenuItem fontColorToolStripMenuItem;
        private ToolStripMenuItem alignmentToolStripMenuItem;
        private ToolStripMenuItem windowToolStripMenuItem;
        private ToolStripMenuItem arrangeIconsToolStripMenuItem;
        private ToolStripMenuItem cascadeToolStripMenuItem;
        private ToolStripMenuItem horizontalToolStripMenuItem;
        private ToolStripMenuItem verticalToolStripMenuItem;
        private ToolStripMenuItem languagesToolStripMenuItem;
        private ToolStripMenuItem ukraineToolStripMenuItem;
        private ToolStripMenuItem englishToolStripMenuItem;
        private ToolStripMenuItem leftToolStripMenuItem;
        private ToolStripMenuItem centerToolStripMenuItem;
        private ToolStripMenuItem rightToolStripMenuItem;
        private ToolStrip toolStrip1;
        private ToolStripButton toolStripButton1;
        private ToolStripButton toolStripButton2;
        private ToolStripButton toolStripButton3;
        private ToolStripButton toolStripButton4;
        private ToolStripButton toolStripButton5;
        private ToolStripButton toolStripButton6;
        private ToolStripButton toolStripButton7;
        private ToolStripButton toolStripButton8;
    }
}

