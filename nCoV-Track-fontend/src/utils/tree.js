const idParam = 'id'
const parentIdParam = 'parentId'
const childrenParam = 'children'
const disableParam = 'disabled'
const root = 0

/**
 * 通过根id构造树
 * @param nodeList 构造数组
 * @param selectAbleIdList 可选择的节点Id
 * @param rootId 根节点id
 * @returns {Array}
 */
export function createTreeByRootId(nodeList, selectAbleIdList, rootId) {
  const arrayCopy = JSON.parse(JSON.stringify(nodeList))
  const selectAbleIdMap = {}
  selectAbleIdList.forEach(item => {
    selectAbleIdMap[item] = true
  })
  return createTree(arrayCopy, selectAbleIdMap, rootId)
}

/**
 * 根据可选择的节点构造树
 * @param nodeList 构造数组
 * @param selectAbleIdList 可选择的节点Id
 * @param selectAbleNodeList 可选择的节点
 */
export function createTreeBySelectAbleNodeList(nodeList, selectAbleIdList, selectAbleNodeList) {
  const arrayCopy = JSON.parse(JSON.stringify(nodeList))
  let nodeLAC = selectAbleNodeList[0]
  const nodeListMap = {}
  const selectAbleIdMap = {}
  arrayCopy.forEach(item => {
    nodeListMap[item[idParam]] = item[parentIdParam]
  })
  selectAbleIdList.forEach(item => {
    selectAbleIdMap[item] = true
  })
  for (let i = 1; i < selectAbleNodeList.length; i++) {
    nodeLAC = getLCA(nodeListMap, nodeLAC, selectAbleNodeList[i])
  }
  return createTree(arrayCopy, selectAbleIdMap, nodeLAC[parentIdParam])
}

export function getChildrenNodeListByNodeId(nodeList, nodeId) {
  return getChild(nodeList, nodeId)
}

/**
 * 根据数组 [{id:1,parentId:0},{id:2,parentId:1}]
 *     map {1:true,2:undefined}
 *     父节点 0
 * 构造树[{id:1,children:[{id:2,disabled:true}]}]
 * @param nodeList 构造数组
 * @param selectAbleMap 可选节点id构成的map
 * @param parentId 父节点id
 * @returns {Array}
 */
function createTree(nodeList, selectAbleMap, parentId) {
  const arrayCopy = Object.assign([], nodeList)
  const res = []
  // 数组从后往前遍历，避免splice导致的数组变动带来的错误
  for (let i = nodeList.length - 1; i >= 0; i--) {
    const item = nodeList[i]
    if (item[parentIdParam] === parentId) {
      arrayCopy.splice(i, 1)
      res.unshift(item)
    }
  }
  for (let i = 0; i < res.length; i++) {
    const item = res[i]
    if (!selectAbleMap[item[idParam].toString()]) {
      item[disableParam] = true
    }
    item[childrenParam] = createTree(arrayCopy, selectAbleMap, item[idParam])
  }
  return res
}

/**
 * 找到最短祖先，通过链表双指针，长的链表先走gap步
 * @param nodeListMap id:parentId
 * @param node1 节点1
 * @param node2 节点2
 * @returns {*}
 */
function getLCA(nodeListMap, node1, node2) {
  const linkList1 = getLinkList(nodeListMap, node1[idParam])
  const linkList2 = getLinkList(nodeListMap, node2[idParam])
  const gap = Math.abs(linkList1.length - linkList2.length)
  let long = []
  let short = []

  if (linkList1.length - linkList2.length > 0) {
    long = linkList1
    short = linkList2
  } else {
    long = linkList2
    short = linkList1
  }
  for (let i = 0; i < short.length; i++) {
    if (short[i][idParam] === long[i + gap][idParam]) {
      return short[i]
    }
  }
}

/**
 * 构造简易链表，链接至root
 * @param nodeListMap id:parentId
 * @param id 当前节点
 * @returns {Array}
 */
function getLinkList(nodeListMap, id) {
  const linkList = []
  let node = { [idParam]: id, [parentIdParam]: nodeListMap[id] }
  do {
    linkList.push(node)
    node = {
      [idParam]: node[parentIdParam],
      [parentIdParam]: nodeListMap[node[parentIdParam]]
    }
  } while (node[idParam] !== root)
  return linkList
}

function getChild(nodeList, id) {
  const arrayCopy = Object.assign([], nodeList)
  const res = []
  const map = { [id]: true }
  let flag = true
  while (flag) {
    flag = false
    for (let i = arrayCopy.length - 1; i >= 0; i--) {
      const item = arrayCopy[i]
      if (map[item[parentIdParam]]) {
        arrayCopy.splice(i, 1)
        res.push(item)
        map[item[idParam]] = true
        flag = true
      }
    }
  }
  return res
}

// const nodeList = [
//   { id: 1, parentId: 0 },
//   { id: 2, parentId: 1 },
//   { id: 3, parentId: 1 },
//   { id: 4, parentId: 3 },
//   { id: 5, parentId: 3 },
//   { id: 6, parentId: 4 },
//   { id: 7, parentId: 4 },
//   { id: 8, parentId: 5 }
// ]
// const a = getChildrenNodeListByNodeId(nodeList, 1)
// console.log(a)

