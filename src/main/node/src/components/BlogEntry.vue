<template>
  <div class="blog-entry">
    <span>{{ entryTitle }}</span>
    <span>{{ entryDate }}</span>
    <div>
      {{ entryText }}
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        entryTitle: '<title here>',
        entryDate: '<entry date>',
        entryText: '<blog text>'
      }
    }
  }
</script>