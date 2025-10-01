import { authService } from '../services/authService';

export function Dashboard() {
  const user = authService.getUser();

  if (!user) {
    return <div>Loading...</div>;
  }

  return (
    <div className="bg-white overflow-hidden shadow rounded-lg">
      <div className="px-4 py-5 sm:p-6">
        <h2 className="text-lg font-medium text-gray-900 mb-4">Dashboard</h2>
        
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          {user.roles.includes('ADMIN') && (
            <>
              <div className="bg-blue-50 p-4 rounded-lg">
                <h3 className="font-semibold text-blue-800">Child Management</h3>
                <p className="text-sm text-blue-600 mt-1">Register and manage children</p>
                <button className="mt-2 text-sm bg-blue-600 text-white px-3 py-1 rounded hover:bg-blue-700">
                  Manage Children
                </button>
              </div>
              
              <div className="bg-green-50 p-4 rounded-lg">
                <h3 className="font-semibold text-green-800">Programs & Classes</h3>
                <p className="text-sm text-green-600 mt-1">Manage programs and classrooms</p>
                <button className="mt-2 text-sm bg-green-600 text-white px-3 py-1 rounded hover:bg-green-700">
                  Manage Programs
                </button>
              </div>
              
              <div className="bg-purple-50 p-4 rounded-lg">
                <h3 className="font-semibold text-purple-800">Enrollment</h3>
                <p className="text-sm text-purple-600 mt-1">Enroll children in programs</p>
                <button className="mt-2 text-sm bg-purple-600 text-white px-3 py-1 rounded hover:bg-purple-700">
                  Manage Enrollment
                </button>
              </div>
            </>
          )}
          
          {user.roles.includes('CAREGIVER') && (
            <div className="bg-yellow-50 p-4 rounded-lg">
              <h3 className="font-semibold text-yellow-800">Caregiver Tools</h3>
              <p className="text-sm text-yellow-600 mt-1">Daily activities and reports</p>
              <button className="mt-2 text-sm bg-yellow-600 text-white px-3 py-1 rounded hover:bg-yellow-700">
                View Tools
              </button>
            </div>
          )}
          
          {user.roles.includes('PARENT') && (
            <div className="bg-pink-50 p-4 rounded-lg">
              <h3 className="font-semibold text-pink-800">My Child</h3>
              <p className="text-sm text-pink-600 mt-1">View your child's information</p>
              <button className="mt-2 text-sm bg-pink-600 text-white px-3 py-1 rounded hover:bg-pink-700">
                View Profile
              </button>
            </div>
          )}
        </div>

        <div className="mt-8 p-4 bg-green-50 rounded">
          <h3 className="font-semibold text-green-800">✅ Story 3 Complete - Navigation Working!</h3>
          <ul className="text-sm text-green-700 mt-2 space-y-1">
            <li>✓ Layout with Sidebar Navigation</li>
            <li>✓ Role-based Menu Items ({user.roles.join(', ')})</li>
            <li>✓ Admin Dashboard Shell</li>
            <li>✓ Placeholder Pages Ready</li>
          </ul>
        </div>
      </div>
    </div>
  );
}