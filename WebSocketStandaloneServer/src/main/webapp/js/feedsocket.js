$(function() {
	var feedSocket = {
		socket: null,
	  	init: function() {
			this.socket = new WebSocket('ws://127.0.0.1:8081/');
		  	this.socket.onopen = this.open;
		  	this.socket.onclose = this.close;
		  	this.socket.onmessage = this.message;
		  	this.socket.onerror = this.error;
	  	},
		open: function() {
		  	console.log('open');
		},
		close: function() {
		  	console.log('close');
		},
		message: function(message) {
			var alert = eval('(' + message.data + ')');


			$('#alerts-principal').fadeOut("slow", function () {
                $('#alerts-principal').empty();
                $.tmpl( 'alertTmpl', alert).appendTo('#alerts-principal');
                $('#alerts-principal').fadeIn("slow", function () {
                     if(previousAlert != null)
                        $.tmpl( 'alertTmpl', previousAlert).prependTo('#alerts-feed');
                     $('#alerts-feed li:first-child').fadeIn("slow");
                });

            });
            previousAlert = alert;



			/*
			$("div").fadeIn(3000, function () {
                        $("span").fadeIn(100);
                      });
           */
		},
		error: function(e) {
		  	console.log(e);
		}
  	};

  	var previousAlert;

  	$.template( "alertTmpl", "<li><b>${user}</b> ${message}</li>" );

  	feedSocket.init();
});