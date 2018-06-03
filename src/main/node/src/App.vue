<template>
  <div id="app">
    <h1>{{ message }} </h1>
    <BlogEntry/>
  </div>
</template>

<script>
import BlogEntry from './components/BlogEntry.vue'

export default {
  name: 'app',
  data () {
    return {
      message: 'Hello!!!'
    }
  },
  components: {
    BlogEntry
  }
}

</script>