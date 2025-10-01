import { ChildList } from '../../components/ChildList';

export function ChildrenPage() {
  return (
    <div>
      <div className="mb-6">
        <h1 className="text-2xl font-bold text-gray-900 dark:text-white">Children Management</h1>
        <p className="text-gray-600 dark:text-gray-300 mt-1">Register and manage children in the daycare</p>
      </div>

      <div className="mb-6">
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-lg font-medium text-gray-900 dark:text-white">All Children</h2>
          <button className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md">
            Add New Child
          </button>
        </div>
        
        <ChildList />
      </div>
    </div>
  );
}