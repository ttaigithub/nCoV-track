import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken, removePlatformUsername } from '@/utils/auth'
import { resetRouter } from '@/router'
import {
  loginDisposablePassword,
  loginQrCode
} from '@/api/tai-security'

const state = {
  token: getToken(),
  name: '',
  avatar: '',
  roles: []
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const { data } = response
        commit('SET_TOKEN', data.token)
        setToken(data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  fakeLogin({ commit }) {
    return new Promise((resolve, reject) => {
      commit('SET_TOKEN', 'token')
      setToken('token')
      resolve(200)
    })
  },
  // 一次性密码登录
  loginDisposablePassword({ commit }, param) {
    return new Promise((resolve, reject) => {
      loginDisposablePassword(param).then(res => {
        if (res.code === 0) {
          commit('SET_TOKEN', res.data.token)
          setToken(res.data.token)
          resolve(200)
        } else if (res.code === '300') {
          resolve(300)
        } else if (res.code === 'E404') {
          resolve(404)
        }
      }).catch(error => {
        reject(error)
      })
    })
  },
  // 二维码登录
  loginQrCode({ commit }, param) {
    return new Promise((resolve, reject) => {
      loginQrCode(param).then(res => {
        if (res.code === 0) {
          commit('SET_TOKEN', res.data.token)
          setToken(res.data.token)
          resolve(200)
        } else if (res.code === 300) {
          resolve(300)
        }
      }).catch(error => {
        reject(error)
      })
    }
    )
  },
  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response
        if (!data) {
          reject('Verification failed, please Login again.')
        }

        const { roles, name, avatar } = data

        // roles must be a non-empty array
        if (!roles || roles.length <= 0) {
          reject('getInfo: roles must be a non-null array!')
        }

        commit('SET_ROLES', roles)
        commit('SET_NAME', name)
        commit('SET_AVATAR', avatar)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        commit('SET_TOKEN', '')
        commit('SET_ROLES', [])
        removeToken()
        removePlatformUsername()
        resetRouter()
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      commit('SET_TOKEN', '')
      commit('SET_ROLES', [])
      removeToken()
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

