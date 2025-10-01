import { useState, useEffect } from 'react'
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom'
import { LoginForm } from './components/auth/LoginForm'
import { Dashboard } from './pages/Dashboard'
import { Layout } from './components/layout/Layout'
import { ChildrenPage } from './pages/admin/ChildrenPage'
import { ProgramsPage } from './pages/admin/ProgramsPage'
import { ClassroomsPage } from './pages/admin/ClassroomsPage'
import { EnrollmentPage } from './pages/admin/EnrollmentPage'
import { ThemeProvider } from './contexts/ThemeContext'
import { authService } from './services/authService'

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState<boolean | null>(null)

  useEffect(() => {
    // Check authentication status on app load
    setIsAuthenticated(authService.isAuthenticated())
  }, [])

  const handleLoginSuccess = () => {
    setIsAuthenticated(true)
  }

  const handleLogout = () => {
    authService.logout()
    setIsAuthenticated(false)
  }

  // Loading state while checking authentication
  if (isAuthenticated === null) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600 mx-auto"></div>
          <p className="mt-4 text-gray-600">Loading...</p>
        </div>
      </div>
    )
  }

  return (
    <ThemeProvider>
      <Router>
        <Routes>
          <Route 
            path="/login" 
            element={
              isAuthenticated ? 
              <Navigate to="/dashboard" replace /> : 
              <LoginForm onLoginSuccess={handleLoginSuccess} />
            } 
          />
          <Route 
            path="/dashboard" 
            element={
              isAuthenticated ? 
              <Layout onLogout={handleLogout}><Dashboard /></Layout> : 
              <Navigate to="/login" replace />
            } 
          />
          {/* Admin Routes */}
          <Route 
            path="/admin/children" 
            element={
              isAuthenticated ? 
              <Layout onLogout={handleLogout}><ChildrenPage /></Layout> : 
              <Navigate to="/login" replace />
            } 
          />
          <Route 
            path="/admin/programs" 
            element={
              isAuthenticated ? 
              <Layout onLogout={handleLogout}><ProgramsPage /></Layout> : 
              <Navigate to="/login" replace />
            } 
          />
          <Route 
            path="/admin/classrooms" 
            element={
              isAuthenticated ? 
              <Layout onLogout={handleLogout}><ClassroomsPage /></Layout> : 
              <Navigate to="/login" replace />
            } 
          />
          <Route 
            path="/admin/enrollment" 
            element={
              isAuthenticated ? 
              <Layout onLogout={handleLogout}><EnrollmentPage /></Layout> : 
              <Navigate to="/login" replace />
            } 
          />
          <Route 
            path="/" 
            element={
              <Navigate to={isAuthenticated ? "/dashboard" : "/login"} replace />
            } 
          />
        </Routes>
      </Router>
    </ThemeProvider>
  )
}

export default App