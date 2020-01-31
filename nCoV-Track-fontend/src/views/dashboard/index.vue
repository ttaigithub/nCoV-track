<template>
  <div class="app-container" />
</template>

<script>

export default {
  name: 'Dashboard',
  data() {
    return {}
  },
  computed: {},
  watch: {},
  mounted() {
    this.$router.push({ path: this.redirect || '/virusDetection' })
  }
}
</script>

<style lang="scss" type="text/scss" scoped>
</style>
