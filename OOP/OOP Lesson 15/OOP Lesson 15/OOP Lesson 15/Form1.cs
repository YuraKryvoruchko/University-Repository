using System.Collections.Generic;
using System.Windows.Forms;

namespace OOP_Lesson_15
{
    public partial class Form1 : Form
    {
        private Dictionary<int, ILessonTask> _lessons;

        private ILessonTask _currentTask;

        public Form1()
        {
            InitializeComponent();
            _lessons = new Dictionary<int, ILessonTask>()
            {
                { 0, new LessonTask1(this.l1TextBox1, this.l1TextBox3, this.l1Button)},
                { 1, new LessonTask2(this.l2TextBoxA, this.l2TextBoxB, this.l2TextBoxdegrees, this.l2TextBoxResult, this.l2Button)},
                { 2, new LessonTask3(this.l3TextBoxA, this.l3TextBoxB, this.l3TextBoxC, this.l3TextBoxD, this.l3TextBoxResult, this.l3Button)},
                { 3, new LessonTask4(this.l4TextBoxA, this.l4TextBoxB, this.l4TextBoxC, this.l4TextBoxResult, this.l4Button)},
                { 4, new LessonTask5(this.l5TextBoxDayResult, this.l5TextBoxYearResult, this.l5Button)},
                { 5, new LessonTask6(this.l6TextBoxAList, this.l6TextBoxBList, this.l6TextBoxResult, this.l6Button)},
                { 6, new LessonTask7(this.l7TextBox, this.l7TextBoxResult, this.l7Button)}
            };

            _currentTask = _lessons[0];
            _currentTask.Init();

            this.tabControl1.SelectTab(0);
            this.tabControl1.Selected += OnChangeTab;
        }

        private void OnChangeTab(object sender, TabControlEventArgs e)
        {
            int tabIndex = tabControl1.SelectedIndex;
            _currentTask.Deinit();
            _currentTask = _lessons[tabIndex];
            _currentTask.Init();
        }
    }
}
