
$(function(){
		//var token = "Bearer" + token<%= session.getAttribute("token")%>;
		//var loginUserId = <%= session.getAttribute("userId") %>;
		//var loginUsername = <%= session.getAttribute("username") %>;
			//handlig navbar
			var user = [[#{user}]] 'default';
			console.log(user);
			if(!user){
			var rootPath = [[${#request.getContextPath()}]] 'default';
			var userPath = rootPath + '/' + user.id;
			
			$("#sign-up-user").attr('target','_blank')
												.attr('href', userPath)
												.html('Welcome ' + user.username);
			$("#sign-up-user span").remove();
      //Log off and redirct to index page
			var $userLogOff = $("#user-log-off");
			$userLogOff.attr('href', rootPath + 'logout')
								 .html('Log Off');
			$userLogOff.on('click', function(e){
				console.log('clear sessionStorage');
				window.localStorage.clear();
				})
			}
});//end ready

function getParaFromUrl() {

	  var url = location.search; //obtain substring after '?'
	   var paraFromUrl = new Object();
	   if (url.indexOf("?") != -1) {
	      var str = url.substr(1);
	      strs = str.split("&");
	      for(var i = 0; i < strs.length; i ++) {
	         paraFromUrl[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
	      }
	   }
	   return paraFromUrl;
	}

