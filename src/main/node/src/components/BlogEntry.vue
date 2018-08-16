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
  export default {
    props: ['post']
  }
</script>