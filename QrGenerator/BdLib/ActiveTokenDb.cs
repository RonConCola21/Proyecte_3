using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static Microsoft.EntityFrameworkCore.DbLoggerCategory.Database;

namespace BdLib
{
    public class ActiveTokenDb
    {
        public int id;
        public DateTime moment_temp;
        public string cadena;
        public int valor;

        public ActiveTokenDb(int id, DateTime moment_temp, string cadena, int valor)
        {
            this.id = id;
            this.moment_temp = moment_temp;
            this.cadena = cadena;
            this.valor = valor;
        }

        #region properties
        public int Id
        {
            get { return id; }
            set { id = value; }
        }

        public DateTime Moment_temp
        {
            get { return moment_temp; }
            set { moment_temp = value; }
        }

        public string Cadena
        {
            get { return cadena; }
            set { cadena = value; }
        }

        public int Value
        {
            get { return valor; }
            set { valor = value; }
        }
        #endregion

        public static Boolean insert(ActiveTokenDb at)
        {
            try
            {
                using (MySQLDBContext context = new MySQLDBContext())
                {
                    using (var connection = context.Database.GetDbConnection())
                    {
                        connection.Open();
                        DbTransaction transaction = connection.BeginTransaction();
                        using (var cmd = connection.CreateCommand())
                        {
                            cmd.CommandText = "insert into active_token(at_id,at_moment_temp, at_cadena, at_value) values (@p_at_id, @p_at_moment_temp, @p_at_cadena, @p_at_value)";
                            DbUtils.afegirParametre(cmd, "p_at_id", null, DbType.Int32);
                            DbUtils.afegirParametre(cmd, "p_at_moment_temp", at.moment_temp, DbType.DateTime);
                            DbUtils.afegirParametre(cmd, "p_at_value", at.valor, DbType.Int32);
                            DbUtils.afegirParametre(cmd, "p_at_cadena", at.cadena, DbType.String);

                            Int32 filesinserides = cmd.ExecuteNonQuery();

                            if (filesinserides == 1)
                            {
                                int updated = cmd.ExecuteNonQuery();
                                if (updated == 1)
                                {
                                    transaction.Commit();
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            catch (Exception ex)
            {

            }
            return false;
        }
    }
}
