
function getSimpleDate(dataTime){
	if(dataTime!=null){
		var dates=new Date(dataTime);
		return dates.getFullYear()+"-"+getFullNum(dates.getMonth()+1)+"-"+getFullNum(dates.getDate());
	}else{
		return "无操作";
	}
}

function getStrDate(dataTime){
	if(dataTime!=null){
		var dates=new Date(dataTime);
		return dates.getFullYear()+"-"+getFullNum(dates.getMonth()+1)+"-"+getFullNum(dates.getDate())+" "+getFullNum(dates.getHours())+":"+getFullNum(dates.getMinutes())+":"+getFullNum(dates.getSeconds());
	}else{
		return "无操作";
	}
}

function getFullNum(num){
	return num<10?"0"+num:""+num;
}