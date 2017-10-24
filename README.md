# syspj3

システム創成プロジェクトⅢ  

＜メンバーを追加する＞  
1.SettingのCollaboratorsに追加したいメンバーのアカウントを加える  
2.追加されるメンバーは自身のアカウントで以下のURLに行く  
  https://github.com/Ebikatsu/Notatum-Medicum/invitation  
3.Acceptをクリックする  
4.完了  

# 環境構築  
## tomcat  
tomcatをインストールするディレクト(シェル上の変数$tomcatにそのパスが入っているとする。)で以下のコマンドを実行する。tomcatのバージョン番号は適宜変更する必要がある。  
```
curl -O http://ftp.riken.jp/net/apache/tomcat/tomcat-9/v9.0.0.M26/bin/apache-tomcat-9.0.0.M26.tar.gz
tar zxvf apache-tomcat-9.0.0.M26.tar.gz
```
上のコマンドを実行するとカレントディレクトリに${tomcat}/apache-tomcat-9.0.0.M26というディレクトリが作成される。  
### tomcatの起動  
```
${tomcat}/apache-tomcat-9.0.0.M26/bin/startup.sh
```
### tomcatの終了  
```
${tomcat}/apache-tomcat-9.0.0.M26/bin/shutdown.sh
```
### tomcatの構造  
```
${tomcat}/apache-tomcat-9.0.0.M26以下の重要なディレクトリ、ファイルについて説明する。  
```
#### ${tomcat}/apache-tomcat-9.0.0.M26/conf/Catalina/localhost  
ここにtomcatで作成するlocalhost:8080のウェブサイトに関する情報を書いたxmlファイルを配置する。ウェブサイト名はシェル上の変数$websiteに、そのウェブサイトのリソースを置く場所は$weblocation入っているものとする。  
ファイル名:${website}.xml  
ファイルの内容  
```
<Context path="/%{website}" docBase="%{weblocation}" reloadable="false"/>
```
ウェブサイトのurlはlocalhost:8080/${website}となる。  
#### ${tomcat}/apache-tomcat-9.0.0.M26/webapps  
一般にここにウェブサイトのリソースを置くが、前述のxmlファイルで場所を指定すればどこに置いてもよい。  
#### ${tomcat}/apache-tomcat-9.0.0.M26/webapps/${website}  
ウェブサイトのリソースが入っているディレクトリ。前述のxmlファイルで場所を指定すればどこに置いてもよい。${weblocation}と同じ。  
#### ${tomcat}/apache-tomcat-9.0.0.M26/bin  
tomcatを起動するstartup.shと終了するshutdown.shがある。  
#### ${tomcat}/apache-tomcat-9.0.0.M26/lib/servlet-api.jar  
ウェブサイトを動かすjavaプログラムが参照するjarファイル。  
javaファイルは基本的に以下のような形になる。  
```
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class PatientList extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException
	{
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
//out.println関数でhtmlのコードを出力する。
	}
}
```
#### ${weblocation}/WEB-INF/classes  
ウェブサイトを動かすjavaプログラムのclassファイルをここに配置する。ここにあるプログラムは${tomcat}/apache-tomcat-9.0.0.M26/lib/servlet-api.jarを参照する必要がある。  
#### ${weblocation}/WEB-INF/web.xml  
ウェブサイトの中にある各ページの情報を記述するファイル。以下のような内容になる。  
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/j2ee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd" version="2.4">
ここに書くページの情報を記述する。
</web-app>
```
ページの情報は以下のように記述する。サーブレット名を$servlet、javaのクラス名を$class、ページ名を$pageとする。  
```
 <servlet>
  <servlet-name>%{servlet}
  </servlet-name>
  <servlet-class>%{class}
  </servlet-class>
 </servlet>
 <servlet-mapping>
  <servlet-name>%{sevlet}
  </servlet-name>
  <url-pattern>/%{page}
  </url-pattern>
 </servlet-mapping>
```
ページのurlはlocalhost:8080/${website}/${page}となる。  
# postgreSQL  
## インストール  
以下のコマンドでインストールできる。  
```
sudo apt-get -y install postgresql
```
## postgresアカウント  
postgreSQLをインストールすると、postgresというlinuxのアカウントが作成される。以下のコマンドによってpostgresのパスワードを変更する。  
```
sudo passwd postgres
```
postgresにログイン  
```
su - postgres
```
postgreSQLの起動  
```
psql
```
postgresからログアウト  
```
exit
```
## postgreSQLのコマンド(一部)  
### 終了  
```
\q
```
### OSのコマンドを実行  
```
\! command
```
### postgreSQLのコマンドの一覧を表示  
```
\?
```
### SQLのコマンドの一覧を表示  
```
\h
```
### SQLの特定のコマンドの使い方を表示  
```
\h command
```
### ロールの一覧を表示  
```
\du
```
### データベースの一覧を表示  
```
\l
```
### テーブルの一覧を表示  
```
\dt
```
## 新しいロールの作成  
### ロール本体の作成  
postgreSQLのアカウントのことをロールという。postgresもロールの一つである。ここではnotatummedicumという名前でパスワードもnotatummedicumのロールを作成する手順を説明する。  
まずpostgresqlに入ってpostgreSQL上で新しいロールを作成する。  
```
su - postgres
psql
\du --ロールの一覧を確認
create role notatummedicum with login password 'notatummedicum'; --ロールの作成
\du --ロールの一覧を確認
```
ロールの作成はcreate roleコマンドで行う。ロール名を指定し、withの後ろにオプションをつける。今回はログインできる権限(login)とパスワード(password 'notatummedicum')をつけた。  
### データベースの作成  
新しく作成したロールでログインするために、そのロールを管理者とし、そのロールと同じ名前のデータベースを作成する必要がある。  
```
\l --データベースの一覧を確認
create database notatummedicum owner notatummedicum; --データベースを作成
\l --データベースの一覧を確認
```
### linuxのアカウントの作成  
postgreSQLではPeer認証のために名前とパスワードがロールと同じlinuxのアカウントがないとそのロールにログインできないので、linux上でアカウントを作る必要がある。  
```
\q --postgreSQLを終了
exit #postgresからログアウト
sudo useradd -m notatummedicum #新しいlinuxのアカウントを作成
sudo paddwd notatummedicum #パスワードの設定
```
### 新しいロールにログイン  
これで新しいロールを作成することができたので、linuxのアカウントnotatummedicumにログインしてpostgreSQLを起動すると新しいロールnotatummedicumにログインすることができる。  
```
su - notatummedicum
psql
```
ログアウトする。  
```
\q
exit
```
# JDBC  
javaでSQL命令を飛ばしてデータベースを操作するツール。今回はpostgreSQLを使用しているのでpostgreSQL用のJDBCを使う。  
## インストール
JDBCをインストールするディレクトリで以下のコマンドを実行する。
```
wget https://jdbc.postgresql.org/download/postgresql-42.1.4.jre7.jar
```
カレントディレクトリにpostgresql-42.1.4.jre7.jarというファイルが保存されるので、JDBCを使ったjavaプログラムをコンパイル、実行する時はこのファイルを参照させる。  
## JDBCのテスト  
JDBCが正しく動作するかテストする。まずpostgreSQLのロールnotatummedicumで、JDBCで操作する用のテーブルを作成する。  
```
su - notatummedicum
psql
\dt --テーブルの一覧を表示
create table test(test text); --テーブル作成
\dt --テーブルの一覧を表示
select * from test; --テーブルtestの中身を確認
insert into test values('test1');
insert into test values('test2');
insert into test values('test3');
select * from test; --テーブルtestの中身を確認
\q --postgreSQLを終了
exit
```
そして、DBTest.javaを作成する。  
```
import java.sql.*;

class DBTest
{
	public static void main(String[] args)
	{
		try
		{
			String driver = "org.postgresql.Driver";
			String url = "jdbc:postgresql://localhost:5432/notatummedicum";		//データベースのURL(jdbc:postgresql://localhost:5432/データベース名)
			String user = "notatummedicum";						//ロール名
			String password = "notatummedicum";					//パスワード
			Class.forName(driver);							//ドライバの読み込み
			Connection con = DriverManager.getConnection(url, user, password);	//データベースへの接続
			Statement stmt = con.createStatement();
			String sql = "select * from test;";					//SQLコマンド
			ResultSet rs = stmt.executeQuery(sql);					//SQLを実行(テーブルtestの全てのレコードを取得)し、結果をrsに格納
			while(rs.next())System.out.println(rs.getString("test"));		//rsから一つずつレコードを取り出し、属性testの値(文字列)を表示する。
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException e)
		{
			System.err.println(e);
		}
		catch(ClassNotFoundException e)
		{
			System.err.println(e);
		}
	}
}
```
そしてコンパイルして実行する。  
```
javac -classpath .:./postgresql-42.1.4.jre7.jar DBTest.java
java -classpath .:./postgresql-42.1.4.jre7.jar DBTest
```
正常に実行されれば、以下のように表示される。  
```
test1
test2
test3
```
jarファイルをクラスパスに追加しておくと  
```
javac DBTest.java
java DBTest
```
で実行することができる。
