import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

// 模拟博客数据
const mockArticles = [
  {
    id: 1,
    title: 'Vue 3 Composition API 最佳实践',
    content: 'Vue 3的Composition API为组件逻辑组织带来了全新的方式...',
    excerpt: '本文详细介绍Vue 3 Composition API的使用技巧和最佳实践',
    category: '前端开发',
    tags: ['Vue', 'JavaScript', '前端'],
    createdAt: '2024-01-15',
    readTime: 5,
    views: 128
  },
  {
    id: 2,
    title: '深入理解JavaScript异步编程',
    content: '异步编程是现代JavaScript开发的核心概念...',
    excerpt: '从回调到async/await，全面解析JavaScript异步编程',
    category: 'JavaScript',
    tags: ['JavaScript', '异步', 'Promise'],
    createdAt: '2024-01-10',
    readTime: 8,
    views: 89
  },
  {
    id: 3,
    title: 'Tailwind CSS实用指南',
    content: 'Tailwind CSS是一个功能类优先的CSS框架...',
    excerpt: '学习如何使用Tailwind CSS快速构建现代化的用户界面',
    category: 'CSS',
    tags: ['CSS', 'Tailwind', '前端'],
    createdAt: '2024-01-05',
    readTime: 6,
    views: 156
  }
]

export const useBlogStore = defineStore('blog', () => {
  const articles = ref(mockArticles)
  const currentArticle = ref(null)
  const searchQuery = ref('')
  const selectedCategory = ref('')
  const selectedTag = ref('')

  // 获取所有分类
  const categories = computed(() => {
    return [...new Set(articles.value.map(article => article.category))]
  })

  // 获取所有标签
  const tags = computed(() => {
    const allTags = articles.value.flatMap(article => article.tags)
    return [...new Set(allTags)]
  })

  // 过滤后的文章列表
  const filteredArticles = computed(() => {
    return articles.value.filter(article => {
      const matchesSearch = article.title.toLowerCase().includes(searchQuery.value.toLowerCase()) ||
                          article.content.toLowerCase().includes(searchQuery.value.toLowerCase())
      const matchesCategory = !selectedCategory.value || article.category === selectedCategory.value
      const matchesTag = !selectedTag.value || article.tags.includes(selectedTag.value)
      
      return matchesSearch && matchesCategory && matchesTag
    })
  })

  // 获取文章详情
  const getArticleById = (id) => {
    const article = articles.value.find(article => article.id === parseInt(id))
    if (article) {
      article.views++ // 增加阅读量
      currentArticle.value = article
    }
    return article
  }

  // 搜索文章
  const searchArticles = (query) => {
    searchQuery.value = query
  }

  // 按分类过滤
  const filterByCategory = (category) => {
    selectedCategory.value = category
    selectedTag.value = ''
  }

  // 按标签过滤
  const filterByTag = (tag) => {
    selectedTag.value = tag
    selectedCategory.value = ''
  }

  // 清除所有过滤器
  const clearFilters = () => {
    searchQuery.value = ''
    selectedCategory.value = ''
    selectedTag.value = ''
  }

  return {
    articles,
    currentArticle,
    categories,
    tags,
    filteredArticles,
    searchQuery,
    selectedCategory,
    selectedTag,
    getArticleById,
    searchArticles,
    filterByCategory,
    filterByTag,
    clearFilters
  }
})