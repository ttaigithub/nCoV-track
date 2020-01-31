import {
  queryPlatformUsername
} from '@/api/platformUser'
import { getPlatformUsername, setPlatformUsername } from '@/utils/auth'

const state = {
  platformUsername: getPlatformUsername()
}

const mutations = {
  SET_PLATFORM_USERNAME: (state, platformUsername) => {
    state.platformUsername = platformUsername
  }
}

const actions = {
  queryPlatformUsername() {
    return new Promise(resolve => {
      queryPlatformUsername().then(res => {
        setPlatformUsername(res.data)
        resolve(true)
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

