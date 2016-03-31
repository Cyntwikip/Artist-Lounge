var notifIndex = 0;

function Notification(name, notificationType, profilepic){
	this.name = name;
	this.notificationType = notificationType;
	this.profilepic = profilepic;
}

function addNotif(Notification){
	var notifName = Notification.name;
	var notifType = Notification.notificationType;
	var profilepic = Notification.profilepic
	var historyIndex = notifIndex;
	
	var idName = 'notif';
	var notif = $('<div></div>');
	$(notif).addClass("notif");
	$(notif).attr("id", idName + notifIndex);
	$("#notifs").append(notif);
	
	var notifID = "#" + idName + notifIndex;
	
	var details = $('<div></div>');
	$(details).addClass("notif-details");
	$(notifID).append(details);
	
	var pic = $('<img></img>');
	$(pic).addClass("notif-dp");
	$(pic).attr("src", profilepic);
	$(details).append(pic);
		
	if(notifType == '1'){
		var name = $('<div></div>');
		$(name).addClass("notif-name");
		$(details).append(name);
		$(name).append(notifName + " wants to add you as friend.");
	}
	
	else if(notifType == '2'){
		var name = $('<div></div>');
		$(name).addClass("notif-name");
		$(details).append(name);
		$(name).append(notifName + " wants to challenge you.");
	}
	
	var acceptBtn = $('<button></button>');
	acceptBtn.html("Accept");
	$(acceptBtn).addClass("btn btn-success accept-button");
	$(details).append(acceptBtn);
	acceptBtn.click(function (){
		details.parent().remove();
		
		var historyidName = 'notif';
		var historynotif = $('<div></div>');
		$(historynotif).addClass("notif");
		$(historynotif).attr("id", idName + historyIndex);
		$("#histories").append(historynotif);
	
		var historynotifID = "#" + idName + historyIndex;
	
		var historydetails = $('<div></div>');
		$(historydetails).addClass("notif-details");
		$(historynotifID).append(historydetails);
	
		var historypic = $('<img></img>');
		$(historypic).addClass("notif-dp");
		$(historypic).attr("src", profilepic);
		$(historydetails).append(historypic);
		
		if(notifType == '1'){
		var historyname = $('<div></div>');
		$(historyname).addClass("notif-name");
		$(historydetails).append(historyname);
		$(historyname).append(notifName + " became friends with you.");
	}
	
	else if(notifType == '2'){
		var historyname = $('<div></div>');
		$(historyname).addClass("notif-name");
		$(historydetails).append(historyname);
		$(historyname).append(notifName + " challenged you.");
	}
	});
		
	var declineBtn = $('<button></button>');
	declineBtn.html("Decline");
	$(declineBtn).addClass("btn btn-danger decline-button");
	$(details).append(declineBtn);
	declineBtn.click(function (){
		details.parent().remove();
	});
	
	notifIndex++;
}