import Vue from 'vue'
import './style.css'
import App from './App.vue'
import BlogEntry from './components/BlogEntry.vue'

new Vue({
  el: '#app',
  render: h => h(App),
  components: {
    BlogEntry
  }
})
