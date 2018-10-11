<template>
  <div class="blog-entry">
    <span>{{ post.title }}</span>
    <span>{{ post.dateCreated }}</span>
    <div>
      {{ post.contents }}
    </div>
  </div>
</template>

<script>
  import marked from 'marked'
  export default {
    props: ['post'],
    computed: {
      compiledMarkdown: function() {
        return marked(this.post.contents, {sanitize: true});
      }
    }
  }
</script>

<style scoped>
    .blog-entry {
        margin: 10px 0 10px 0;
        background-color: aliceblue;
    }
</style>