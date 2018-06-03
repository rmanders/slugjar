import Vue from 'vue'
import './style.css';

var app = new Vue({
  el: 'body',
  data: {
    message: 'Dude'
  }
})


function component() {
  var element = document.createElement('div');

  element.innerHTML = "hi there, {{ message }}!";
  element.classList.add('hello');

  return element;
}

//document.body.appendChild(component());