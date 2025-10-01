export function ClassroomsPage() {
  return (
    <div>
      <div className="mb-6">
        <h1 className="text-2xl font-bold text-gray-900 dark:text-white">Classrooms Management</h1>
        <p className="text-gray-600 dark:text-gray-300 mt-1">Manage classroom spaces and capacity</p>
      </div>

      <div className="bg-white dark:bg-gray-800 shadow rounded-lg p-6">
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-lg font-medium text-gray-900 dark:text-white">All Classrooms</h2>
          <button className="bg-purple-600 hover:bg-purple-700 text-white px-4 py-2 rounded-md">
            Add New Classroom
          </button>
        </div>
        
        <div className="border-2 border-dashed border-gray-200 dark:border-gray-600 rounded-lg p-8 text-center">
          <div className="text-gray-400 dark:text-gray-500 mb-2">
            <svg className="mx-auto h-12 w-12" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h2a1 1 0 011 1v5m-4 0h4" />
            </svg>
          </div>
          <h3 className="text-sm font-medium text-gray-900 dark:text-white mb-1">No classrooms yet</h3>
          <p className="text-sm text-gray-500 dark:text-gray-400 mb-4">
            Set up classroom spaces and manage capacity.
          </p>
          <button className="bg-purple-600 hover:bg-purple-700 text-white px-4 py-2 rounded-md text-sm">
            Create First Classroom
          </button>
        </div>

        <div className="mt-6 p-4 bg-yellow-50 dark:bg-yellow-900 rounded">
          <p className="text-sm text-yellow-800 dark:text-yellow-200">
            💡 <strong>Coming Soon:</strong> Classroom setup, capacity management, and space allocation will be available in Story 4.
          </p>
        </div>
      </div>
    </div>
  );
}