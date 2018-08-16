<template>
  <div id="app">
    <h1>{{ message }} </h1>
    <BlogEntry
            v-for="post in posts"
            v-bind:key="post.slug"
            v-bind:post="post"
    ></BlogEntry>
    <div>
      {{ posts }}
    </div>
  </div>
</template>

<script>
import BlogEntry from './components/BlogEntry.vue'
import axios from 'axios'

export default {
  name: 'app',
  data () {
    return {
      message: 'Hello!!!',
      posts: []
    }
  },
  components: {
    BlogEntry
  },
  mounted() {
    axios
      .get('http://localhost:8080/api/blog/posts')
      .then(response => (this.posts = response.data))
  }
}

</script>