var wsUri = "ws://localhost:9000/socket";
  var output;

  function init()
  {
    output =$(".main-content");
    testWebSocket();
  }

  function testWebSocket()
  {
    websocket = new WebSocket(wsUri);
    websocket.onopen = function(evt) { onOpen(evt) };
    websocket.onclose = function(evt) { onClose(evt) };
    websocket.onmessage = function(evt) { onMessage(evt) };
    websocket.onerror = function(evt) { onError(evt) };
  }

  function onOpen(evt)
  {
   // writeToScreen("CONNECTED");
    doSend("WebSocket rocks");
  }

  function onClose(evt)
  {
  //  writeToScreen("DISCONNECTED");
  }

  function onMessage(evt)
  {
	  if (window.console && console.log) {
		    console.log(evt.data); //for firebug
		  }else{
			  dom_notify(evt.data);
		  }
	  var obj = jQuery.parseJSON(evt.data);
	  dom_notify(obj.msg);
	  
	  $('#ttbody').append(obj.data);
	  
   // writeToScreen('<span style="color: blue;">RESPONSE: ' + evt.data+'</span>');
   // websocket.close();
  }

  function onError(evt)
  {
	  dom_notify("Error : : "+ evt.data);
  //  writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
  }

  function doSend(message)
  {
   // writeToScreen("SENT: " + message); 
   // websocket.send(message);
  }

  function writeToScreen(message)
  {
    var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = message;
    output.append(pre);
  }

  window.addEventListener("load", init, false);
  
  
  function dom_notify(msg) {
	  $('#notify').text(msg);
	  $('#notify').fadeIn();
	  setTimeout(function() {
	    $('#notify').fadeOut
	    ();
	  }, 2000);
	}
