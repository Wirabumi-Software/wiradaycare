import { ReactNode, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { authService } from '../../services/authService';

interface LayoutProps {
  children: ReactNode;
  onLogout: () => void;
}

export function Layout({ children, onLogout }: LayoutProps) {
  const user = authService.getUser();
  const location = useLocation();
  const [isSidebarOpen, setIsSidebarOpen] = useState(true);

  const handleLogout = () => {
    authService.logout();
    onLogout();
  };

  if (!user) {
    return <div>Loading...</div>;
  }

  // Navigation items based on user role
  const navigationItems = [
    // Admin-only items
    ...(user.roles.includes('ADMIN') ? [
      { name: 'Dashboard', href: '/dashboard', icon: '🏠' },
      { name: 'Children', href: '/admin/children', icon: '👶' },
      { name: 'Programs', href: '/admin/programs', icon: '🎓' },
      { name: 'Classrooms', href: '/admin/classrooms', icon: '🏫' },
      { name: 'Enrollment', href: '/admin/enrollment', icon: '📝' },
    ] : []),
    
    // Caregiver items
    ...(user.roles.includes('CAREGIVER') ? [
      { name: 'Dashboard', href: '/dashboard', icon: '🏠' },
      { name: 'My Classes', href: '/caregiver/classes', icon: '👥' },
      { name: 'Activities', href: '/caregiver/activities', icon: '🎨' },
    ] : []),
    
    // Parent items
    ...(user.roles.includes('PARENT') ? [
      { name: 'My Child', href: '/parent/child', icon: '👨‍👩‍👧‍👦' },
    ] : []),
  ];

  const isActive = (href: string) => location.pathname === href;

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Top Navigation */}
      <nav className="bg-white shadow-sm border-b">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between h-16">
            <div className="flex items-center space-x-4">
              <button
                onClick={() => setIsSidebarOpen(!isSidebarOpen)}
                className="text-gray-600 hover:text-gray-900 p-2 rounded-md hover:bg-gray-100"
                title={isSidebarOpen ? "Hide menu" : "Show menu"}
              >
                {isSidebarOpen ? (
                  <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                  </svg>
                ) : (
                  <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16M4 18h16" />
                  </svg>
                )}
              </button>
              <h1 className="text-xl font-semibold text-gray-900">WiraDayCare</h1>
            </div>
            <div className="flex items-center space-x-4">
              <span className="text-sm text-gray-700">Welcome, {user.username}</span>
              <span className="text-xs bg-blue-100 text-blue-800 px-2 py-1 rounded">
                {user.roles.join(', ')}
              </span>
              <button
                onClick={handleLogout}
                className="text-sm bg-gray-600 hover:bg-gray-700 text-white px-3 py-2 rounded"
              >
                Logout
              </button>
            </div>
          </div>
        </div>
      </nav>

      <div className="flex">
        {/* Side Navigation */}
        {isSidebarOpen && (
          <aside className="w-64 bg-white shadow-sm min-h-screen transition-all duration-300">
            <div className="py-4">
              <nav className="space-y-1 px-3">
                {navigationItems.map((item) => (
                  <Link
                    key={item.name}
                    to={item.href}
                    className={`
                      flex items-center px-3 py-2 rounded-md text-sm font-medium transition-colors
                      ${isActive(item.href)
                        ? 'bg-blue-100 text-blue-700 border-r-2 border-blue-700'
                        : 'text-gray-700 hover:bg-gray-100 hover:text-gray-900'
                      }
                    `}
                  >
                    <span className="mr-3">{item.icon}</span>
                    {item.name}
                  </Link>
                ))}
              </nav>
            </div>
          </aside>
        )}

        {/* Main Content */}
        <main className={`flex-1 py-6 px-4 sm:px-6 lg:px-8 transition-all duration-300 ${
          isSidebarOpen ? '' : 'ml-0'
        }`}>
          {children}
        </main>
      </div>
    </div>
  );
}