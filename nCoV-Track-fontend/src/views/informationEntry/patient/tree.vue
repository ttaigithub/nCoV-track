<template>
  <div>
    <el-button type="text" @click="dialogVisible = true">{{ selectedNode }}</el-button>
    <el-dialog
      title="父权限"
      :visible.sync="dialogVisible"
      width="30%"
      append-to-body
    >
      <el-tree
        :props="props"
        :data="tree"
        node-key="id"
        :default-expanded-keys="defaultExpandedKeys"
        :expand-on-click-node="false"
      >
        <span
          slot-scope="{ node, data }"
          class="custom-tree-node"
          @click="selectNode(node, data)"
        >
          <span>{{ node.label }}</span>
          <el-button
            type="text"
            size="mini"
            :disabled="disableIdList.indexOf(data.id)>=0"
          >
            选择
          </el-button>
        </span>
      </el-tree>
    </el-dialog>
  </div>
</template>

<script>
import {
  queryPermissionListAll
} from '@/api/informationEntry/permission'
import { createTreeByRootId, getChildrenNodeListByNodeId } from '@/utils/tree'

export default {
  name: 'Dashboard',
  components: {},
  props: {
    parentPermissionId: {
      type: Number,
      default: -1
    },
    selfPermissionId: {
      type: Number,
      default: -1
    },
    selectCallback: {
      type: Function,
      default: (node, data) => {
      }
    }
  },
  data() {
    return {
      dialogVisible: false,
      props: {
        label: 'name'
      },
      tree: [],
      nodeList: [],
      disableIdList: []
    }
  },
  computed: {
    defaultExpandedKeys() {
      if (this.tree.length === 0) {
        return [-1]
      }
      return [this.parentPermissionId]
    },
    selectedNode() {
      if (this.parentPermissionId === -1) {
        return '请选择'
      }
      for (let i = 0; i < this.nodeList.length; i++) {
        if (this.nodeList[i].id === this.parentPermissionId) {
          return this.nodeList[i].name
        }
      }
      return '父权限缺失，请选择'
    }
  },
  watch: {
    dialogVisible() {
      if (!this.dialogVisible) {
        return
      }
      this.queryNodeList()
        .then(() => {
          this.createTree()
          this.getDisableIdList()
        })
    }
  },
  mounted() {
    // 获取nodeList，用于computed:selectedNode
    this.queryNodeList()
      .then(() => {
        this.createTree()
      })
  },
  methods: {
    selectNode(node, data) {
      if (this.disableIdList.indexOf(data.id) >= 0) {
        return
      }
      this.selectCallback(node, data)
      this.dialogVisible = false
    },
    queryNodeList() {
      return new Promise((resolve, reject) => {
        queryPermissionListAll()
          .then(res => {
            this.nodeList = res.data
            resolve()
          })
          .catch(error => {
            reject(error)
          })
      })
    },
    createTree() {
      this.tree = createTreeByRootId(this.nodeList, this.nodeList.map(item => item.id), 0)
    },
    getDisableIdList() {
      this.disableIdList = getChildrenNodeListByNodeId(this.nodeList, this.selfPermissionId).map(item => item.id)
      this.disableIdList.push(this.selfPermissionId)
    }
  }
}
</script>

<style lang="scss" type="text/scss" scoped>
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
  }
</style>
