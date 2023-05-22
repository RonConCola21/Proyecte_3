using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BdLib
{
    class MySQLDBContext : DbContext
    {
        protected override void OnConfiguring(DbContextOptionsBuilder optionBuilder)
        {
            //optionBuilder.UseSqlite("Filename=db/empresa.db");
            //optionBuilder.UseSqlite($"Filename={DB_PATH}/{DB_FILENAME}");
            optionBuilder.UseMySQL("Server=127.0.0.1;Port=3306;Database=JukeApp;Uid=root;Pwd=;");
        }
    }
}
