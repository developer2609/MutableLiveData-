import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.application.databinding.ItemRv1Binding
import com.example.application.databinding.ItemRv2Binding
import com.example.application.models.MyObject

class ViewPagerAdapter (val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class VH1(var itemRv1Binding: ItemRv1Binding):
        RecyclerView.ViewHolder(itemRv1Binding.root) {
        fun onBind() {
          itemRv1Binding.edtName.addTextChangedListener {
              MyObject.mutableLiveData.postValue(it.toString())
          }

        }
    }
    inner class VH2(var itemListBinding: ItemRv2Binding):
        RecyclerView.ViewHolder(itemListBinding.root) {
        fun onBind() {

     MyObject.mutableLiveData.observe(lifecycleOwner){
         itemListBinding.textName.text=it
     }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType==0) {
            return VH1(ItemRv1Binding.inflate(LayoutInflater.from(parent.context), parent, false))

        }else{
            return  VH2(ItemRv2Binding.inflate(LayoutInflater.from(parent.context),parent,false))
        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position==0){
            (holder as VH1).onBind()
        }else{
            (holder as VH2).onBind()

        }

    }

    override fun getItemCount(): Int = 2

    override fun getItemViewType(position: Int): Int {
        return position
        return super.getItemViewType(position)
    }



}