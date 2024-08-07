# JAVA
basic <br />
method <br />
listCRUD <br />
inheritance <br />
oop, pet, collection <br />
textIO, stream <br />
err <br />
thread, serialization <br />
network, doc, networkPDS <br />
jdbc<br />


# DOC
DocClient, DocServer<br />
1. DocServer <br />
   가. "서버 대기 중..."<br />
   나. 무한 대기 (accept())<br />
   다. "클라이언트 접속됨"<br />
   라. UserWorkThread.start();<br /><br />
2. DocClient<br />
   가. 서버에 접속 new socket<br />
   나. 클라이언트 종료<br /><br />
3. 업로드(a), 목록(s), 검색(f), 수정(u), 삭제(d), 종료(x)<br />
   가. 업로드(a)<br />
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(1) 파일명: sample.jpg<br />
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(2) 파일 메모리에 로드(byte[]), 파일명(fname) --> ChatMsg<br />
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(3) 서버에 전송 (upload=true, fname="sample.jpg", fdata=filedata)<br /><br />
4. 파일 업로드 성공시마다 속성들을 파일에 저장한다<br />
   가. 번호, 파일명, 작성자, 날짜, 설명<br />
   나. FileInfo 클래스<br />
   다. List<FileInfo> 구조로 파일에 저장(직렬화)<br />
   라. 파일명 : list_fileinfo.ser<br /><br />
5. if(cm.upload) { // 파일 수신/서버시스템에 저장<br />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; } else if(cm.list){ // list_fileinfo.ser을 로드하여 fileList 변수에 할당<br />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; } else if(cm.find){ // 클라이언트가 검색하려는 경우<br />
	 }<br /><br />
6. 서버측 쓰레드(UserWorkThread)에서 메뉴를 클라이언트에게 보여주기<br />
   가. cm.menu = "업로드(a), 목록(s), 검색(f), 수정(u), 삭제(d), 종료(x):";<br />
   나. oos.writeObject(cm);<br />
   다. oos.flush();<br /><br />
7. 이용자가 파일 업로드하는 경우<br />
	 String m = kbd.nextLine().trim();<br />
	 if(m.eqluals("a")) { // 파일 업로드<br />
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;파일명 : sample.jpg<br />
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;설명   : 홍길동의 증명사진<br />
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;작성자 : 홍길동<br />
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;chatMsg cm = new ChatMsg();<br />
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;cm.upload = true;<br />
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;cm.fname = fio.load(sample.jpg);<br />
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;cd.fdata = 파일 로드 후 생성된 byte[]<br />
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;cm.author = 홍길동;<br />
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;cm.desc = 홍길동의 증명사진<br />
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;oos.writeobject(cm);<br />
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;oos.flush();<br />
	 }<br /><br />
8. 서버에 파일을 저장하는 절차<br />
   가. 클라이언트가 전송한 파일을 서버측 파일 시스템에 저장한다<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(1) FileOutputStream fout = new FileOutputStream("C:/test/upload/" + cm.fname);<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(2) fout.write(cm.fdata);<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(3) fout.close();<br /><br />
   나. 서버에 저장한 파일의 정보를 FileInfo 클래스의 인스턴스에 저장한다<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(1) FileInfo fi = new FileInfo(); // 번호, 파일명, 작성자, 파일크기, 날짜, 설명<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(2) fi.setFname(cm.fname);<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(3) fi.setFsize(cm.fsize);<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(4) fi.setFuid(cm.uid);<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(5) fi.setFDate(cm.Date());<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(6) fi.setFDesc(cm.desc);<br /><br />
   다. 직렬화된 List<FileInfo> 데이터를 역질렬화하여 List<FileInfo>의 참조를 구한다<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(1) FileIO fio = new FileIO();<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(2) List<FileInfo> list = (List<FileInfo>)fio.deserilize(); //역직렬화<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(3) list.get(list.size()-1).getFno + 1;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(4) fi.setFNO(nextFno);<br /><br />
   라. List<FileInfo>의 참조를 사용하여 새로 추가된 파일의 정보를 리스트에 저장한다<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(1) list.add(fi);<br /><br />
   마. List<fileInfo>가 직렬화되어 저장되는 파일의 이름은 list_fileinfo.ser으로 한다<br /><br />
   바. 내용이 변경된 list를 다시 파일에 직렬화하여 저장한다<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(1) FileIO fio = new FileIO();<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(2) fio.serialize(list);<br /><br />
   사. 클라이언트에서 업로드 성공 메시지를 출력한다<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ChatMsg cm = new ChatMsg();<br />
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;cm.msg = "업로드 성공"<br />
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;oos.writeObject(cm);<br />
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;oos.flush();<br />
