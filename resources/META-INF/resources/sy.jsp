<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8"> 
    <link rel="stylesheet" type="text/css" href="css/sy.css">
    <title>欢迎光临旗袍小屋</title>
</head>
<body>


	<div class="header">
	 <li class="headerInner">
			首页
		</li>
		<a href="rc.html">
		<li class="headerInner">
			日常文艺
		</li></a>
		<a href="xp.html">
		<li class="headerInner">
			新品尝鲜
		</li></a>
		<a href="rm.html">
		<li class="headerInner">
			热卖推荐
		</li></a>
		<a href="dk.html">
		<li class="headerInner">
			短款旗袍
		</li></a>
		<a href="ck.html">
		<li class="headerInner">
			长款旗袍
		</li></a>
<!-- 		<a href="login.html"> -->
		<li class="headerInner">
			
			欢迎来到旗袍小屋
		</li>
		</a>

	 

<li class="h1">  
<img src="images/2.gif">
  </li>
<div class="wrapper">
		<div class="banner">
			<ul class="imgList">
				<li class="imgon">
					<div class="innera" style="background-image: url(images/1.gif);opacity:1;">
						
					</div>
				</li>
				<li class="imgon"><div class="innera" style="background-image: url(images/3.gif);opacity:0;"></div>
				</li>
				<li class="imgon">
					<div class="innera" style="background-image: url(images/2.jpg);
					opacity:0;"></div>
				</li>
			</ul>
		</div>
	</div>
	<script>
		var ul1=document.getElementsByClassName("imgList")[0];
		var imgL=document.getElementsByClassName("innera");
		var index=document.getElementsByClassName("index")[0];
		var l=-1;
		setInterval(function e(){

			var count=0;
			var timer1=setInterval(function a(){
				imgL[l%3].style.opacity=parseFloat(imgL[l%3].style.opacity)-0.05;
				imgL[(l+1)%3].style.opacity=parseFloat(imgL[(l+1)%3].style.opacity)+0.05;
				count++;

				if(count==25){
					window.clearInterval(timer1);
				}
			
			},10)
			l++;
		},4000)
	</script>

<li class="h2">  
<img src="images/121.gif"></li>
   <li class="h3">  
<img src="images/222.gif"></li>
<li class="h5"></li>
<li class="h4">  
<img src="images/5.jpg"></li>
<a href="sy.html">
  <li class="h6">  
<img src="images/12.gif"></li>
</a>

<li class="h7">  
<img src="images/db.jpg"></li>




  















  <table cellspacing="0" cellpadding="2" width="100%" align="center" border="0"><tbody>
<tr><td height="30" align="center">
<font color="grey">
	<font size="1px">
	 © 2019-2025   All Rights Reserved  闽ICP备09009508号 
		<div style="display:none"> 

		</div><br> 本站所有内容都由互联网提供，本站仅为站长提供储存纪念平台，所有版权归原权利人,只供学习交流，不做商业用途，如若有侵权行为，请联系站长立即删除! <br>
		本站拒绝一切非法事宜，发现请立即向管理员举报：
			<br>   @qq1746323817.com <img src="images/lianxi.jpg">

</font>
			 </font>
</td>
</tr>
</tbody>
</table>





</body>
</html>