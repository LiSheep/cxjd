function sideBar_menu(){
 function addEvent(el,name,fn){//���¼�
  if(el.addEventListener) return el.addEventListener(name,fn,false);
  return el.attachEvent('on'+name,fn);
 }
 function nextnode(node){//Ѱ����һ���ֵܲ��޳��յ��ı��ڵ�
  if(!node)return ;
  if(node.nodeType == 1)
   return node;
  if(node.nextSibling)
   return nextnode(node.nextSibling);
 }
 function prevnode(node){//Ѱ����һ���ֵܲ��޳��յ��ı��ڵ�
  if(!node)return ;
  if(node.nodeType == 1)
   return node;
  if(node.previousSibling)
   return prevnode(node.previousSibling);
 }
 function parcheck(self,checked){//�ݹ�Ѱ�Ҹ���Ԫ�أ����ҵ�inputԪ�ؽ��в���
  var par =  prevnode(self.parentNode.parentNode.parentNode.previousSibling),parspar;

 }
 function sibcheck(self){//�ж��ֵܽڵ��Ƿ��Ѿ�ȫ��ѡ��
  var sbi = self.parentNode.parentNode.parentNode.childNodes,n=0;
  for(var i=0;i<sbi.length;i++){
   if(sbi[i].nodeType != 1)//���ں��ӽ���а����յ��ı��ڵ㣬���������ۼƳ��ȵ�ʱ��ҲҪ����ȥ
    n++;
   else if(sbi[i].getElementsByTagName('input')[0].checked)
    n++;
  }
  return n==sbi.length?true:false;
 }
 addEvent(document.getElementById('sideBar_menu'),'click',function(e){//��input����¼���ʹ��sideBar_menu��Ԫ�ش���
  e = e||window.event;
  var target = e.target||e.srcElement;
  var tp = nextnode(target.parentNode.nextSibling);
  switch(target.nodeName){
   case 'A'://���A��ǩչ������������Ŀ¼�����ı�����ʽ��ѡ��checkbox
    if(tp&&tp.nodeName == 'UL'){
     if(tp.style.display != 'none' ){
      tp.style.display = 'none';    
     }else{
      tp.style.display = 'block';
     
     }
    }
    break;

 

  }
 });


}
