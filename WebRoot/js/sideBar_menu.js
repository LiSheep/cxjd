function sideBar_menu(){
 function addEvent(el,name,fn){//绑定事件
  if(el.addEventListener) return el.addEventListener(name,fn,false);
  return el.attachEvent('on'+name,fn);
 }
 function nextnode(node){//寻找下一个兄弟并剔除空的文本节点
  if(!node)return ;
  if(node.nodeType == 1)
   return node;
  if(node.nextSibling)
   return nextnode(node.nextSibling);
 }
 function prevnode(node){//寻找上一个兄弟并剔除空的文本节点
  if(!node)return ;
  if(node.nodeType == 1)
   return node;
  if(node.previousSibling)
   return prevnode(node.previousSibling);
 }
 function parcheck(self,checked){//递归寻找父亲元素，并找到input元素进行操作
  var par =  prevnode(self.parentNode.parentNode.parentNode.previousSibling),parspar;

 }
 function sibcheck(self){//判断兄弟节点是否已经全部选中
  var sbi = self.parentNode.parentNode.parentNode.childNodes,n=0;
  for(var i=0;i<sbi.length;i++){
   if(sbi[i].nodeType != 1)//由于孩子结点中包括空的文本节点，所以这里累计长度的时候也要算上去
    n++;
   else if(sbi[i].getElementsByTagName('input')[0].checked)
    n++;
  }
  return n==sbi.length?true:false;
 }
 addEvent(document.getElementById('sideBar_menu'),'click',function(e){//绑定input点击事件，使用sideBar_menu根元素代理
  e = e||window.event;
  var target = e.target||e.srcElement;
  var tp = nextnode(target.parentNode.nextSibling);
  switch(target.nodeName){
   case 'A'://点击A标签展开和收缩树形目录，并改变其样式会选中checkbox
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
