import Vue from 'vue'
import './style.css';
import printMe from './print.js';

new Vue({
  el: '#app',
  data: {
    message: 'Dude'
  }
});

function component() {
  var element = document.createElement('div');
  var btn = document.createElement('button');

  element.innerHTML = "hi there, {{ message }}!";
  element.id = "app"
  element.classList.add('hello');

  btn.innerHTML = 'Click me and check the console!';
  btn.onclick = printMe;

  element.appendChild(btn);
  return element;
}

document.body.appendChild(component());