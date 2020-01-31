import Cookies from 'js-cookie'

const TokenKey = 'token'
const PlatformUsernameKey = 'platformUsername'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  console.log('set', token)
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function getPlatformUsername() {
  return Cookies.get(PlatformUsernameKey)
}

export function setPlatformUsername(platformUsername) {
  return Cookies.set(PlatformUsernameKey, platformUsername)
}

export function removePlatformUsername() {
  return Cookies.remove(PlatformUsernameKey)
}
