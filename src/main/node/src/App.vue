<template>
  <div id="app">
    <h1>{{ message }} </h1>
    <BlogEntry title="test title" entryDate="2018-06-20" contents="This is a test entry"/>
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