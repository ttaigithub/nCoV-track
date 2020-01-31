<template>
  <div class="navbar">
    <hamburger :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />

    <breadcrumb class="breadcrumb-container" />

    <div class="right-menu">
      <div style="color:#97a8be">DEMO版本V0.1</div>
      <!-- <el-dropdown class="avatar-container" trigger="click">
         <div class="avatar-wrapper">
           &lt;!&ndash;<img :src="avatar+'?imageView2/1/w/80/h/80'" class="user-avatar">&ndash;&gt;
           <el-avatar icon="el-icon-user-solid" />
           &lt;!&ndash;<i class="el-icon-caret-bottom" />&ndash;&gt;
         </div>
         <el-dropdown-menu slot="dropdown" class="user-dropdown">
           &lt;!&ndash;<router-link to="/">&ndash;&gt;
           &lt;!&ndash;<el-dropdown-item>&ndash;&gt;
           &lt;!&ndash;Home&ndash;&gt;
           &lt;!&ndash;</el-dropdown-item>&ndash;&gt;
           &lt;!&ndash;</router-link>&ndash;&gt;
           &lt;!&ndash;<a target="_blank" href="https://github.com/PanJiaChen/vue-admin-template/">&ndash;&gt;
           &lt;!&ndash;<el-dropdown-item>Github</el-dropdown-item>&ndash;&gt;
           &lt;!&ndash;</a>&ndash;&gt;
           &lt;!&ndash;<a target="_blank" href="https://panjiachen.github.io/vue-element-admin-site/#/">&ndash;&gt;
           &lt;!&ndash;<el-dropdown-item>Docs</el-dropdown-item>&ndash;&gt;
           &lt;!&ndash;</a>&ndash;&gt;
           <el-dropdown-item>
             &lt;!&ndash;<el-dropdown-item divided>&ndash;&gt;
             <span style="display:block;" @click="logout">退出</span>
           </el-dropdown-item>
         </el-dropdown-menu>
       </el-dropdown>-->
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'

export default {
  components: {
    Breadcrumb,
    Hamburger
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar'
    ])
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    }
  }
}
</script>

<style lang="scss" type="text/scss" scoped>
  .navbar {
    width: 100%;
    height: 50px;
    overflow: hidden;
    position: relative;
    background: #fff;
    box-shadow: 0 1px 4px rgba(0, 21, 41, .08);

    .hamburger-container {
      line-height: 46px;
      height: 100%;
      float: left;
      cursor: pointer;
      transition: background .3s;
      -webkit-tap-highlight-color: transparent;

      &:hover {
        background: rgba(0, 0, 0, .025)
      }
    }

    .breadcrumb-container {
      float: left;
    }

    .right-menu {
      float: right;
      height: 100%;
      line-height: 50px;
      margin-right: 16px;

      &:focus {
        outline: none;
      }

      .right-menu-item {
        display: inline-block;
        padding: 0 8px;
        height: 100%;
        font-size: 18px;
        color: #5a5e66;
        vertical-align: text-bottom;

        &.hover-effect {
          cursor: pointer;
          transition: background .3s;

          &:hover {
            background: rgba(0, 0, 0, .025)
          }
        }
      }

      .avatar-container {
        margin-right: 30px;

        .avatar-wrapper {
          margin-top: 5px;
          position: relative;

          .user-avatar {
            cursor: pointer;
            width: 40px;
            height: 40px;
            border-radius: 10px;
          }

          .el-icon-caret-bottom {
            cursor: pointer;
            position: absolute;
            right: -20px;
            top: 25px;
            font-size: 12px;
          }
        }
      }
    }
  }

  .user-dropdown {
    margin: -15px -15px 0 0;
  }
</style>
