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
	 + "서버 대기중..."<br />
	 + 무한 대기(accept())<br />
	 + "클라이언트 접속됨"<br />
	 + UserWorkThread.start();<br /><br />
2. DocClient<br />
	 + 서버에 접속 new socket<br />
	 + 클라이언트 종료<br /><br />
3. 업로드(a), 목록(s), 검색(f), 수정(u), 삭제(d), 종료(x)<br />
	 + 업로드(a)<br />
	   - 파일명: sample.jpg<br />
	   - 파일 메모리에 로드(byte[]), 파일명(fname) --> ChatMsg<br />
	   - 서버에 전송 (upload=true, fname="sample.jpg", fdata=filedata)<br /><br />
4. 파일 업로드 성공시마다 속성들을 파일에 저장한다<br />
	 + 번호, 파일명, 작성자, 날짜, 설명<br />
	 + FileInfo 클래스<br />
	 + List<FileInfo> 구조로 파일에 저장(직렬화)<br />
	 + 파일명 : list_fileinfo.ser<br /><br />
5. if(cm.upload) {<br />
	 // 파일 수신/서버시스템에 저장<br />
	 } else if(cm.list){<br />
	 // list_fileinfo.ser을 로드하여 fileList 변수에 할당<br />
	 } else if(cm.find){<br />
	 // 클라이언트가 검색하려는 경우<br />
	 }<br /><br />
6. 서버측 쓰레드(UserWorkThread)에서 메뉴를 클라이언트에게 보여주기<br />
	 + cm.menu = "업로드(a), 목록(s), 검색(f), 수정(u), 삭제(d), 종료(x):";<br />
	 + oos.writeObject(cm);<br />
	 + oos.flush();<br /><br />
7. 이용자가 파일 업로드하는 경우<br />
	 String m = kbd.nextLine().trim();<br />
	 if(m.eqluals("a")) { // 파일 업로드<br />
	     파일명 : sample.jpg<br />
	     설명   : 홍길동의 증명사진<br />
	     작성자 : 홍길동<br />
	     chatMsg cm = new ChatMsg();<br />
	     cm.upload = true;<br />
	     cm.fname = fio.load(sample.jpg);<br />
	     cd.fdata = 파일 로드 후 생성된 byte[]<br />
	     cm.author = 홍길동;<br />
	     cm.desc = 홍길동의 증명사진<br />
	     oos.writeobject(cm);<br />
	     oos.flush();<br />
	 }<br /><br />
8. 서버에 파일을 저장하는 절차<br />
	 + 클라이언트가 전송한 파일을 서버측 파일 시스템에 저장한다<br />
	   - FileOutputStream fout = new FileOutputStream("C:/test/upload/" + cm.fname);<br />
	   - fout.write(cm.fdata);<br />
	   - fout.close();<br />
	 + 서버에 저장한 파일의 정보를 FileInfo 클래스의 인스턴스에 저장한다<br />
	   - FileInfo fi = new FileInfo(); // 번호, 파일명, 작성자, 파일크기, 날짜, 설명<br />
	   - fi.setFname(cm.fname);<br />
	   - fi.setFsize(cm.fsize);<br />
	   - fi.setFuid(cm.uid);<br />
	   - fi.setFDate(cm.Date());<br />
	   - fi.setFDesc(cm.desc);<br />
	 + 직렬화된 List<FileInfo> 데이터를 역질렬화하여 List<FileInfo>의 참조를 구한다<br />
	   - FileIO fio = new FileIO();<br />
	   - List<FileInfo> list = (List<FileInfo>)fio.deserilize(); //역직렬화<br />
	   - list.get(list.size()-1).getFno + 1;<br />
	   - fi.setFNO(nextFno);<br />
	 + List<FileInfo>의 참조를 사용하여 새로 추가된 파일의 정보를 리스트에 저장한다<br />
	   - list.add(fi);<br />
	 + List<fileInfo>가 직렬화되어 저장되는 파일의 이름은 list_fileinfo.ser으로 한다<br />
	 + 내용이 변경된 list를 다시 파일에 직렬화하여 저장한다<br />
	   - FileIO fio = new FileIO();<br />
	   - fio.serialize(list);<br />
	 + 클라이언트에서 업로드 성공 메시지를 출력한다<br />
	   - ChatMsg cm = new ChatMsg();<br />
	     cm.msg = "업로드 성공"<br />
	     oos.writeObject(cm);<br />
	     oos.flush();<br />
