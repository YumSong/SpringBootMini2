//把表单转换出json对象
$.fn.toJson = function() {
	var self = this,
		json = {},
		push_counters = {},
		patterns = {
			"validate" : /^[a-zA-Z][a-zA-Z0-9_]*(?:\[(?:\d*|[a-zA-Z0-9_]+)\])*$/,
			"key" : /[a-zA-Z0-9_]+|(?=\[\])/g,
			"push" : /^$/,
			"fixed" : /^\d+$/,
			"named" : /^[a-zA-Z0-9_]+$/
		};

	this.build = function(base, key, value) {
		base[key] = value;
		return base;
	};

	this.push_counter = function(key) {
		if (push_counters[key] === undefined) {
			push_counters[key] = 0;
		}
		return push_counters[key]++;
	};

	$.each($(this).serializeArray(), function() {
		// skip invalid keys
		if (!patterns.validate.test(this.name)) {
			return;
		}

		var k,
			keys = this.name.match(patterns.key),
			merge = this.value,
			reverse_key = this.name;

		while ((k = keys.pop()) !== undefined) {
			// adjust reverse_key
			reverse_key = reverse_key.replace(new RegExp("\\[" + k + "\\]$"), '');

			// push
			if (k.match(patterns.push)) {
				merge = self.build([], self.push_counter(reverse_key), merge);
			}

			// fixed
			else if (k.match(patterns.fixed)) {
				merge = self.build([], k, merge);
			}

			// named
			else if (k.match(patterns.named)) {
				merge = self.build({}, k, merge);
			}
		}

		json = $.extend(true, json, merge);
	});

	return json;
};



//将josn对象赋值给form
$.fn.fromJson = function(obj) {
	var key,
		value,
		tagName,
		type,
		arr;

	//this.reset();

	for (var x in obj) {
		if (obj.hasOwnProperty(x)) {
			key = x;
			value = obj[x];

			this.find("[name='" + key + "'],[name='" + key + "[]']").each(function() {
				tagName = $(this)[0].tagName.toUpperCase();
				type = $(this).attr('type');
				if (tagName == 'INPUT') {
					if (type == 'radio') {
						if ($(this).val() == value) {
							$(this).attr('checked', true);
						}
					} else if (type == 'checkbox') {
						arr = value.split(',');
						for (var i = 0; i < arr.length; i++) {
							if ($(this).val() == arr[i]) {
								$(this).attr('checked', true);
								break;
							}
						}
					} else {
						$(this).val(value);
					}
				} else if (tagName == 'SELECT' || tagName == 'TEXTAREA') {
					$(this).val(value);
				}
			});
		}
	}
}
/**
 * @author 陈志评
 * 
 * @requires jQuery
 * 
 * 时间格式化
 * 
 * @returns object
 */
Date.prototype.format = function(format) {
	if (!format) {
		format = "yyyy-MM-dd hh:mm:ss";
	}
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
			.substr(4 - RegExp.$1.length));
	}
	for (var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
				: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};
function fomatDateTime(str) {
	return (new Date(parseInt(str.substring(str.indexOf('(') + 1, str
		.indexOf(')'))))).format("yyyy-MM-dd hh:mm:ss");
}

function fomatDate(str) {
	return (new Date(parseInt(str.substring(str.indexOf('(') + 1, str
		.indexOf(')'))))).format("yyyy-MM-dd");
}
function getWebPath() {
	var path_web;
	if (typeof path == "undefined" || typeof path.web == "undefined") {
		var durl = /http[s]?:\/\/([^\/]+)\/([^\/]+)\//g;
		path_web = location.href.match(durl)[0];
	} else {
		path_web = path.web;
	}

	return path_web;
}
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return "";
}
var SQLRUNNER_URL = getWebPath() + 'sqlrunner/';
var sqlrunner = {
	run : function(type, mapperAndRunId, param, callBack) {
		var _url = SQLRUNNER_URL + type + "/" + mapperAndRunId.replace(".", "/");
		$.post(_url, param, callBack);
	},
	sel : function(m, p, c) {
		this.run("select", m, p, c);
	},
	ins : function(m, p, c) {
		this.run("insert", m, p, c);
	},
	del : function(m, p, c) {
		this.run("delete", m, p, c);
	},
	upd : function(m, p, c) {
		this.run("update", m, p, c);
	}
}
// 简单的表格
function loadGrid(id, mapper,query) {
	var gridVue = new Vue({
		el : "#" + id,
		data : {
			query : $.extend({},query),
			rows : [],
			page : {
				curPage : 1,
				pageSize : 10,
				btnSize : 10,
				pageTotal : 0,
				btns : []
			}
		},
		methods : {
			pre : function(b) {
				this.page.curPage--;
				this.loadData();
			},
			next : function() {
				this.page.curPage++;
				this.loadData();
			},
			calcBtn : function(t) {
				var p = this.page;
				p.btns = [];
				var x = parseInt(t / p.pageSize);
				var ap = t % p.pageSize > 0 ? x + 1 : x;
				this.page.pageTotal = ap;
				var a = p.btnSize / 2;
				var b = p.curPage - a < 1 ? 1 : p.curPage - a; //碰左侧
				b = p.curPage + a >= ap ? ap - p.btnSize - 1 : b;
				b = b < 1 ? 1 : b;
				if (b != 1) {
					p.btns.push(1);
				}
				for (var i = b; i <= ap && i <= p.btnSize + b; i++) {
					p.btns.push(i);
				}
				if (ap != p.btns[p.btns.length - 1]) {
					p.btns.push(ap);
				}
			},
			loadData : function(_page) {
				if (Number(_page) > 0) {
					this.page.curPage = _page;
				}
				var t = this;
				var p = t.page;
				var param = $.extend({
					page : p.curPage,
					limit : p.pageSize
				}, this.query);
				sqlrunner.sel(mapper, param, function(data) {
					console.info(data);
					if ("S1000" != data.code) {
						alert("数据加载异常，请重试或联系管理员");
					}
					t.$data.rows = data.rows;
					t.calcBtn(data.total);
				});
			}
		}
	});
	gridVue.loadData();
	return gridVue;
}

function uploadImg(t, imgId,hiddenInputName,call) {
	if (t.files.length < 1) {
		return;
	}
	var wp= getWebPath();
	var formData = new FormData();
	formData.append('file', t.files[0]);
	$.ajax({
		url : wp+"uploadFile",
		type : "post",
		data : formData,
		dataType : "json",
		contentType : false,
		processData : false,
		mimeType : "multipart/form-data",
		success : function(data) {
			$("#" + imgId).attr("src", wp+"visit/" + data.data);
			var i = $(t).next("input[name=" + hiddenInputName + "]");
			if (i.length == 0) {
				var a = $('<input type="hidden"/>').attr("name", hiddenInputName).attr("value", data.data);
				$(t).after(a);
			} else {
				i.attr("value", data.data);
			}
			if (call) {
				call(data, t);
			}
		},
		error : function(data) {
			alert("上传异常");
		}
	});
}

