export function ChildrenPage() {
  return (
    <div>
      <div className="mb-6">
        <h1 className="text-2xl font-bold text-gray-900 dark:text-white">Children Management</h1>
        <p className="text-gray-600 dark:text-gray-300 mt-1">Register and manage children in the daycare</p>
      </div>

      <div className="bg-white dark:bg-gray-800 shadow rounded-lg p-6">
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-lg font-medium text-gray-900 dark:text-white">All Children</h2>
          <button className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md">
            Add New Child
          </button>
        </div>
        
        <div className="border-2 border-dashed border-gray-200 rounded-lg p-8 text-center">
          <div className="text-gray-400 mb-2">
            <svg className="mx-auto h-12 w-12" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197m13.5-9a2.5 2.5 0 11-5 0 2.5 2.5 0 015 0z" />
            </svg>
          </div>
          <h3 className="text-sm font-medium text-gray-900">Coming in Story 4</h3>
          <p className="text-sm text-gray-500">Child registration form and list will be implemented next</p>
        </div>
      </div>
    </div>
  );
}