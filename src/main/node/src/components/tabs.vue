<template>
    <div>
        <ul>
            <li v-for="tab in tabs" :class="{ 'is-active': tab.isActive }">
                <a :href="tab.href" @click="selectTab(tab)">{{ tab.name }}</a>
            </li>
        </ul>
    </div>
</template>

<script>
  export default {
    name: "tabs",
    props: {
      name: { required: true },
      selected: {default: false }
    },
    data() {
      return {tabs: [] };
    },
    created() {
      this.tabs = this.$children;
    },
    methods: {
      selectTab(selectedTab) {
        this.tabs.forEach(tab => {
          tab.isActive = (tab.name === selectedTab.name);
        });
      }
    }
  }
</script>

<style scoped>

</style>