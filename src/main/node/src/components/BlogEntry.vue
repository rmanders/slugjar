<template>
  <div class="blog-entry">
    <span>{{ title }}</span>
    <span>{{ entryDate }}</span>
    <div>
      {{ contents }}
    </div>
  </div>
</template>

<script>
  export default {
    props: [
      'title',
      'entryDate',
      'contents'
    ]
  }
</script>