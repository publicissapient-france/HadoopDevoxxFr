
 function connect(){
     var host = window.location.host;
     if(host.indexOf(':')>0){
        host = host.substring(0, host.indexOf(':'));
     }
     var wsHost = "ws://" + host + ":8090/admin/client";

    try{
        socket = new WebSocket(wsHost);

        console.log("Socket Status: "+socket.readyState);
        console.log(wsHost);

        socket.onopen = function(){
       	    console.log("Socket Status: "+socket.readyState+' (open)');
        }
        socket.onmessage = function(message){
            console.log("Received: " + message.data);

            var alert = eval('(' + message.data + ')');

            if (alert.job === "occurence") {
                plotTopWords(alert);
            } else {
               $('#twittsLeader').fadeOut("slow", function () {
                  var previousAlert= new Object();
                  previousAlert.message=$('#topTwittMessage').text();
                  previousAlert.user=$('#topTwittUser').text();
                  previousAlert.count=$('#topTwittCount').text();

                                                                                                                                                                                    console.log(previousAlert);
                  $('#twittsLeader').empty();
                  $( "#twittsLeaderLabelTmpl").tmpl().appendTo('#twittsLeader');
                  //$( "#twittTemplate" ).tmpl(alert).appendTo('#twittsLeader');
                  $( "#twittTemplate" ).tmpl(alert).insertAfter('#twittsLeaderLabel');
                  $('#twittsLeader').fadeIn("slow", function () {
                      if(previousAlert.message.length > 0)
                              $( "#twittTemplate" ).tmpl( previousAlert ).insertAfter('#historyLabel');
                              //$( "#twittTemplate" ).tmpl( previousAlert ).prependTo('#twitts');
                      //$('#twitts div:first-child').fadeIn("slow");
                      $('#twittsLeader').listview('refresh');
                      $('#twittshistory').listview('refresh');
                  });
               });
            }
        }
        socket.onclose = function(){
            console.log("Socket Status: "+socket.readyState+' (Closed)');
            retryConnect();
        }
    } catch(exception){
       console.log("Error"+exception);
    }
}

    var plotData = [];
    var plot;
    var MAX_TOP_WORD = 10;

    var updatePlotData = function(message) {
        if (plotData.length < MAX_TOP_WORD) {
            plotData.push([message.word, message.count]);
        } else {
            var dataAlreadyPresent = _(plotData).find(function(item) {
                return item[0] === message.word;
            });

            if (dataAlreadyPresent !== undefined) {
                dataAlreadyPresent[1] = message.count;
            } else {
                var dataWithMinOccurence = _(plotData).min(function(item) {
                    return item[1];
                });

                if (dataWithMinOccurence !== undefined && message.count > dataWithMinOccurence[1]) {
                    dataWithMinOccurence[0] = message.word;
                    dataWithMinOccurence[1] = message.count;
                }
            }
        }
    };

    var plotTopWords = function(message) {
        updatePlotData(message);

        if (plot !== undefined) {
            plot.destroy();
        }

        plot = $.jqplot('topWords', [plotData], {
            series:[
                {renderer:$.jqplot.BarRenderer}
            ],
            axesDefaults: {
                tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                tickOptions: {
                    angle: -30,
                    fontSize: '11pt'
                }
            },
            axes: {
                xaxis: {
                    renderer: $.jqplot.CategoryAxisRenderer
                }
            }
        });
    };

    var socket;

    function retryConnect(){
        while(socket==null || socket.readyState==3 ){
            console.log("Trying to connect");
            connect();
        }
    }


/*$(function() {
	var feedSocket = {
		socket: null,
	  	init: function() {
	  	    this.socket = new WebSocket('ws://localhost:8081/');
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
		  	feedSocket.init();
		},
		message: function(message) {
		    console.log(message.data);
			var alert = eval('(' + message.data + ')');

			$('#alerts-principal').fadeOut("slow", function () {
    $('#alerts-principal').empty();
    $.tmpl( 'alertTmpl', alert).appendTo('#alerts-principal');
    $('#alerts-principal').fadeIn("slow", function () {
         if(previousAlert != null)
      //$.tmpl( 'alertTmpl2', previousAlert).prependTo('#alerts-feed');
      $( "#alertTmpl2" ).tmpl( previousAlert ).prependTo('#alerts-feed');
         $('#alerts-feed li:first-child').fadeIn("slow");
    });
      });
      previousAlert = alert;
		},
		error: function(e) {
		  	console.log(e);
		}
  	};

  	var previousAlert;

  	$.template( "alertTmpl", "<li><b>${user}</b> ${message}</li>" );

  	feedSocket.init();
});  */
