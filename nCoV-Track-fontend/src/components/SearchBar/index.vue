<template>
  <div>
    <el-card class="wrap">
      <el-form ref="form" :model="form" label-width="80px">
        <el-row :gutter="16" class="single-search">
          <el-col :span="12">
            <el-select v-model="singleSearchValue" class="single-search-left" placeholder="请选择">
              <el-option
                v-for="item in searchOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
            <el-input
              v-if="singleSearchInput"
              v-model="form[singleSearchValue]"
              class="single-search-right"
              placeholder="请输入内容"
            />
            <el-select
              v-if="singleSearchSelect"
              v-model="form[singleSearchValue]"
              class="single-search-right"
              placeholder="请选择"
            >
              <el-option
                v-for="item in singleSearchSelectOption"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-col>
          <el-col :span="6">
            <el-button type="primary" icon="el-icon-search" round @click="search">搜索</el-button>
            <el-button v-if="!searchMore" icon="el-icon-arrow-down" round @click="handleSearchMore">高级搜索</el-button>
            <el-button v-if="searchMore" icon="el-icon-arrow-up" round @click="handleSearchMore">高级搜索</el-button>
          </el-col>
          <el-col :span="6">
            <el-button type="success" icon="el-icon-refresh" round style="float: right" @click="refresh">刷新</el-button>
          </el-col>
        </el-row>
        <el-row v-if="searchMore" :gutter="32" class="more-search">
          <el-col v-for="item in searchOptions" :key="item.value" :span="8">
            <el-form-item :label="item.label">
              <el-input
                v-if="item.type==='input'"
                v-model="form[item.value]"
                size="small"
                placeholder="请输入内容"
              />
              <el-select
                v-if="item.type==='select'"
                v-model="form[item.value]"
                size="small"
                placeholder="请选择"
              >
                <el-option
                  v-for="option in item.options"
                  :key="option.value"
                  :label="option.label"
                  :value="option.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'TaiSecurityQrCode',
  props: {
    searchOptions: {
      type: Array,
      default: () => {
        return [
          {
            value: 'input',
            label: 'input',
            type: 'input'
          },
          {
            value: 'select',
            label: 'select',
            type: 'select',
            options: [
              {
                value: 's1',
                label: 's1'
              }
            ]
          }
        ]
      }
    },
    searchCallback: {
      type: Function,
      default: () => {
      }
    },
    refreshCallback: {
      type: Function,
      default: () => {
      }
    }
  },
  data() {
    return {
      loading: true,
      // 高级搜索是否开启
      searchMore: false,
      // 单搜素时，确定对那个值进行搜索
      singleSearchValue: '',
      form: {}
    }
  },
  computed: {
    // 单搜素时,查询框是否为input
    singleSearchInput() {
      let result = false
      this.searchOptions.forEach(item => {
        if (item.value === this.singleSearchValue && item.type === 'input') {
          result = true
        }
      })
      return result
    },
    // 单搜素时,查询框是否为select
    singleSearchSelect() {
      let result = false
      this.searchOptions.forEach(item => {
        if (item.value === this.singleSearchValue && item.type === 'select') {
          result = true
        }
      })
      return result
    },
    // 单搜素时,查询框为select时的选项
    singleSearchSelectOption() {
      let result = false
      this.searchOptions.forEach(item => {
        if (item.value === this.singleSearchValue && item.type === 'select') {
          result = item.options
        }
      })
      return result
    }
  },
  watch: {
    singleSearchValue() {
      this.form = {}
    }
  },
  mounted() {
    this.singleSearchValue = this.searchOptions[0].value
    this.form = {}
  },
  methods: {
    // 初始化
    refresh() {
      this.singleSearchValue = this.searchOptions[0].value
      this.form = {}
      this.refreshCallback()
    },
    // 搜索
    search() {
      this.searchCallback(this.form)
    },
    // 高级搜索
    handleSearchMore() {
      this.searchMore = !this.searchMore
    }
  }
}
</script>

<style lang="scss" type="text/scss" scoped>
  /*.card {*/
  .wrap {
    position: relative;
    cursor: pointer;
    .single-search {
      .single-search-left {
        width: 150px;
        display: inline-block;
      }
      .single-search-left /deep/ .el-input__inner {
        border-radius: 20px 0 0 20px;
        border-right: 1px dashed #DCDFE6;
      }
      .single-search-left /deep/ .el-input__inner:focus {
        border-right: 1px dashed #409EFF;
      }
      .single-search-right {
        width: calc(100% - 155px);
        margin-left: -4px;
        display: inline-block;
      }
      .single-search-right /deep/ .el-input__inner {
        border-radius: 0 20px 20px 0;
        border-left: 1px dashed #DCDFE6;
      }
      .single-search-right /deep/ .el-input__inner:focus {
        border-left: 1px dashed #409EFF;
      }
    }
    .more-search {
      margin-top: 32px;
      .el-select {
        width: 100%
      }
    }
  }
</style>
