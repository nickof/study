using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApp1.data
{

   public class table : IEquatable<table>,IComparable<table>
    {

        public bool Equals(table other)
        {
            if (other == null) return false;
            else return Equals(other);
        }

        public int CompareTo(table other)
        {
            if (other == null)
                return 1;
            else
                return this.colNum.CompareTo(other.colNum);
        }

        public table() { }

        public table(bool colCheck,String colDevice,String colNum)
        {
            this.colCheck = colCheck;
            this.colDevice = colDevice;
            this.colNum = colNum;
        }

        public override string ToString()
        {
            return colCheck + "," + colDevice + "," + colNum;
        }

        public String colNum { get; set; }
        private bool _colCheck;

        public bool colCheck
        {
            get
            {
                return _colCheck;
            }
            set
            {
                _colCheck = value;
            }
        }

        private String _colDevice;
        public string colDevice { get => _colDevice; set => _colDevice = value; }

    }
}
