import Vue from 'vue'
import './style.css'
import App from './App.vue'

new Vue({
  el: '#app',
  render: h => h(App)
})


function component() {
  var element = document.createElement('div');

  element.innerHTML = "hi there, {{ message }}!";
  element.classList.add('hello');

  return element;
}

//document.body.appendChild(component());